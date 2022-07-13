/*
 * Copyright 2021 Alexander Karkossa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.remindme.app.ui.component

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.SnapSpec
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ResistanceConfig
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.SwipeableState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
typealias RevealState = SwipeableState<RevealValue>

/**
 * Return an alternative value if whenClosure is true. Replaces if/else
 */
private fun <T> T.or(orValue: T, whenClosure: T.() -> Boolean): T {
  return if (whenClosure()) orValue else this
}

// TODO modify/optimize this

/**
 * @param onContentClick called on click
 * @param closeOnContentClick if true, returns to unrevealed state on content click
 */
@ExperimentalMaterialApi
@Composable
fun RevealSwipe(
  modifier: Modifier = Modifier,
  enableSwipe: Boolean = true,
  onContentClick: (() -> Unit)? = null,
  closeOnContentClick: Boolean = true,
  maxRevealDp: Dp = 100.dp,
  maxAmountOfOverflow: Dp = 250.dp,
  directions: Set<RevealDirection> = setOf(
    RevealDirection.Start,
    RevealDirection.End
  ),
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  state: RevealState = rememberRevealState(),
  hiddenContentStart: @Composable RowScope.() -> Unit = {},
  hiddenContentEnd: @Composable RowScope.() -> Unit = {},
  content: @Composable (Shape) -> Unit
) {

  val shape: CornerBasedShape = MaterialTheme.shapes.medium
  val closeOnContentClickHandler = remember(coroutineScope, state) {
    {
      if (state.targetValue != RevealValue.Default) {
        coroutineScope.launch {
          state.reset()
        }
      }
    }
  }

  Box {
    var shapeSize: Size by remember { mutableStateOf(Size(0f, 0f)) }

    val density = LocalDensity.current

    val cornerRadiusBottomEnd = remember(shapeSize, density) {
      shape.bottomEnd.toPx(
        shapeSize = shapeSize,
        density = density
      )
    }
    val cornerRadiusTopEnd = remember(shapeSize, density) {
      shape.topEnd.toPx(
        shapeSize = shapeSize,
        density = density
      )
    }

    val cornerRadiusBottomStart = remember(shapeSize, density) {
      shape.bottomStart.toPx(
        shapeSize = shapeSize,
        density = density
      )
    }
    val cornerRadiusTopStart = remember(shapeSize, density) {
      shape.topStart.toPx(
        shapeSize = shapeSize,
        density = density
      )
    }

    val minDragAmountForStraightCorner =
      max(cornerRadiusTopEnd, cornerRadiusBottomEnd)

    val cornerFactorEnd =
      (-state.offset.value / minDragAmountForStraightCorner).nonNaNorZero().coerceIn(0f, 1f)
        .or(0f) {
          directions.contains(RevealDirection.End).not()
        }

    val cornerFactorStart =
      (state.offset.value / minDragAmountForStraightCorner).nonNaNorZero().coerceIn(0f, 1f).or(0f) {
        directions.contains(RevealDirection.Start).not()
      }

    val animatedCornerRadiusTopEnd = lerp(cornerRadiusTopEnd.dp, cornerFactorEnd.dp, 0F)
    val animatedCornerRadiusBottomEnd = lerp(cornerRadiusBottomEnd.dp, cornerFactorEnd.dp, 0F)

    val animatedCornerRadiusTopStart = lerp(cornerRadiusTopStart.dp, cornerFactorStart.dp, 0F)
    val animatedCornerRadiusBottomStart = lerp(cornerRadiusBottomStart.dp, cornerFactorStart.dp, 0F)

    val animatedShape = shape.copy(
      bottomStart = CornerSize(animatedCornerRadiusBottomStart),
      bottomEnd = CornerSize(animatedCornerRadiusBottomEnd),
      topStart = CornerSize(animatedCornerRadiusTopStart),
      topEnd = CornerSize(animatedCornerRadiusTopEnd)
    )

    // alpha for background
    val maxRevealPx = with(LocalDensity.current) { maxRevealDp.toPx() }
    val draggedRatio =
      (state.offset.value.absoluteValue / maxRevealPx.absoluteValue).coerceIn(0f, 1f)

    // cubic parameters can be evaluated here https://cubic-bezier.com/
    val alpha = CubicBezierEasing(0.4f, 0.4f, 0.17f, 0.9f).transform(draggedRatio)

    // non swipable with hidden content
    Card(
      modifier = Modifier
        .matchParentSize(),
      elevation = 0.dp
    ) {
      Row(
        modifier = Modifier
          .fillMaxSize()
          .alpha(alpha),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(),
          horizontalArrangement = Arrangement.Start,
          verticalAlignment = Alignment.CenterVertically,
          content = hiddenContentStart
        )
        Row(
          modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(),
          horizontalArrangement = Arrangement.End,
          verticalAlignment = Alignment.CenterVertically,
          content = hiddenContentEnd
        )
      }
    }

    CompositionLocalProvider {
      Box(
        modifier = modifier
          .then(
            if (enableSwipe) Modifier
              .offset {
                IntOffset(
                  state.offset.value.roundToInt(),
                  0
                )
              }
              .revealSwipe(
                state = state,
                maxRevealPx = maxRevealPx,
                maxAmountOfOverflow = maxAmountOfOverflow,
                directions = directions
              )
            else Modifier
          )
          .then(
            if (onContentClick != null && !closeOnContentClick) {
              Modifier.clickable(
                onClick = onContentClick
              )
            } else if (onContentClick == null && closeOnContentClick) {
              // if no onContentClick handler passed, add click handler with no indication to enable close on content click
              Modifier.clickable(
                onClick = closeOnContentClickHandler,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
              )
            } else if (onContentClick != null && closeOnContentClick) {
              // decide based on state:
              // 1. if open, just close without indication
              // 2. if closed, call click handler
              Modifier.clickable(
                onClick =
                {
                  val isOpen = state.targetValue != RevealValue.Default
                  // if open, just close. No click event.
                  if (isOpen) {
                    closeOnContentClickHandler()
                  } else {
                    onContentClick()
                  }
                },
                // no indication if just closing
                indication = if (state.targetValue != RevealValue.Default) null else LocalIndication.current,
                interactionSource = remember { MutableInteractionSource() }
              )
            } else Modifier
          )
      ) {
        content(animatedShape)
      }
      // This box is used to determine shape size.
      // The box is sized to match it's parent, which in turn is sized according to its first child - the card.
      BoxWithConstraints(modifier = Modifier.matchParentSize()) {
        shapeSize = Size(constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat())
      }
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
private fun Modifier.revealSwipe(
  maxRevealPx: Float,
  maxAmountOfOverflow: Dp,
  directions: Set<RevealDirection>,
  state: RevealState,
) = composed {

  val maxAmountOfOverflowPx = with(LocalDensity.current) { maxAmountOfOverflow.toPx() }

  val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

  val anchors = mutableMapOf(0f to RevealValue.Default)

  if (RevealDirection.Start in directions) anchors += maxRevealPx to RevealValue.RevealedEnd
  if (RevealDirection.End in directions) anchors += -maxRevealPx to RevealValue.RevealedStart

  val thresholds = { _: RevealValue, _: RevealValue ->
    FractionalThreshold(0.5f)
  }

  val minFactor =
    if (RevealDirection.Start in directions) SwipeableDefaults.StandardResistanceFactor else SwipeableDefaults.StiffResistanceFactor
  val maxFactor =
    if (RevealDirection.End in directions) SwipeableDefaults.StandardResistanceFactor else SwipeableDefaults.StiffResistanceFactor

  Modifier.swipeable(
    state = state,
    anchors = anchors,
    thresholds = thresholds,
    orientation = Orientation.Horizontal,
    enabled = true, // state.value == RevealValue.Default,
    reverseDirection = isRtl,
    resistance = ResistanceConfig(
      basis = maxAmountOfOverflowPx,
      factorAtMin = minFactor,
      factorAtMax = maxFactor
    )
  )
}

private fun Float.nonNaNorZero() = if (isNaN()) 0f else this

enum class RevealDirection {
  Start,
  End
}

/**
 * Possible values of [RevealState].
 */
enum class RevealValue {
  /**
   * Indicates the component has not been revealed yet.
   */
  Default,

  /**
   * Fully revealed to end
   */
  RevealedEnd,

  /**
   * Fully revealed to start
   */
  RevealedStart,
}

/**
 * Create and [remember] a [RevealState] with the default animation clock.
 *
 * @param initialValue The initial value of the state.
 * @param confirmStateChange Optional callback invoked to confirm or veto a pending state change.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberRevealState(
  initialValue: RevealValue = RevealValue.Default,
  confirmStateChange: (RevealValue) -> Boolean = { true },
): RevealState {
  return rememberSwipeableState(
    initialValue = initialValue,
    confirmStateChange = confirmStateChange
  )
}

/**
 * Reset the component to the default position, with an animation.
 */
@ExperimentalMaterialApi
suspend fun RevealState.reset() {
  animateTo(
    targetValue = RevealValue.Default,
  )
}

/**
 * Reset the component to the default position, with an animation.
 */
@ExperimentalMaterialApi
suspend fun RevealState.resetFast() {
  animateTo(
    targetValue = RevealValue.Default,
    anim = SnapSpec(1)
  )
}
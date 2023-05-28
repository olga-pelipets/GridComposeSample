package com.example.components.checkbox

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.example.components.MinimumTouchTargetModifier
import com.example.components.clickableWithoutRipple
import com.example.components.theme.AppTheme
import com.google.android.material.math.MathUtils.lerp
import kotlin.math.floor
import kotlin.math.max

@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    style: CheckboxStyles = checkboxStyle(),
    text: String = "",
    checkboxTouchTargetSize: DpSize = LocalViewConfiguration.current.minimumTouchTargetSize,
    spaceAfterCheckBox: Dp = AppTheme.dimens.normal_100
) {
    Row(
        modifier = modifier
            .clickableWithoutRipple(interactionSource) {
                onCheckedChange?.invoke(!checked)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            spaceAfterCheckBox
        )
    ) {
        TriStateCheckbox(
            state = ToggleableState(checked),
            onClick = if (onCheckedChange != null) {
                { onCheckedChange(!checked) }
            } else {
                null
            },
            interactionSource = interactionSource,
            enabled = enabled,
            style = style,
            modifier = Modifier,
            checkboxTouchTargetSize = checkboxTouchTargetSize
        )
        if (text.isNotEmpty()) {
            Text(
                text = text,
                style = style.textStyle.copy(
                    color = if (enabled) {
                        style.textEnabledColor
                    } else {
                        style.textDisabledColor
                    }
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TriStateCheckbox(
    state: ToggleableState,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    style: CheckboxStyles,
    checkboxTouchTargetSize: DpSize
) {
    val toggleableModifier =
        if (onClick != null) {
            Modifier.triStateToggleable(
                state = state,
                onClick = onClick,
                enabled = enabled,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = style.checkboxRippleRadius,
                    color = style.checkboxRippleColor
                )
            )
        } else {
            Modifier
        }
    CheckboxImpl(
        enabled = enabled,
        value = state,
        modifier = modifier
            .then(
                if (onClick != null) {
                    if (LocalMinimumTouchTargetEnforcement.current) {
                        MinimumTouchTargetModifier(checkboxTouchTargetSize)
                    } else {
                        Modifier
                    }
                } else {
                    Modifier
                }
            )
            .then(toggleableModifier)
            .padding(style.checkboxDefaultPadding),
        style = style
    )
}

@Suppress("UpdateTransitionLabel", "TransitionPropertiesLabel")
@Composable
private fun CheckboxImpl(
    enabled: Boolean,
    value: ToggleableState,
    modifier: Modifier,
    style: CheckboxStyles
) {
    val transition = updateTransition(value)
    val checkDrawFraction by transition.animateFloat(
        transitionSpec = {
            when {
                initialState == ToggleableState.Off -> tween(style.checkAnimationDuration)
                targetState == ToggleableState.Off -> snap(style.boxOutDuration)
                else -> spring()
            }
        }
    ) {
        when (it) {
            ToggleableState.On -> 1f
            ToggleableState.Off -> 0f
            ToggleableState.Indeterminate -> 1f
        }
    }

    val checkCenterGravitationShiftFraction by transition.animateFloat(
        transitionSpec = {
            when {
                initialState == ToggleableState.Off -> snap()
                targetState == ToggleableState.Off -> snap(style.boxOutDuration)
                else -> tween(durationMillis = style.checkAnimationDuration)
            }
        }
    ) {
        when (it) {
            ToggleableState.On -> 0f
            ToggleableState.Off -> 0f
            ToggleableState.Indeterminate -> 1f
        }
    }
    val checkCache = remember { CheckDrawingCache() }
    val checkColor by checkmarkColor(style, enabled, value)
    val boxColor by boxColor(style, enabled, value)
    val borderColor by borderColor(style, enabled, value)
    Canvas(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .requiredSize(style.checkboxSize)
    ) {
        val strokeWidthPx = floor(style.strokeWidth.toPx())
        drawBox(
            boxColor = boxColor,
            borderColor = borderColor,
            radius = style.cornerRadius.toPx(),
            strokeWidth = strokeWidthPx
        )
        drawCheck(
            checkColor = checkColor,
            checkFraction = checkDrawFraction,
            crossCenterGravitation = checkCenterGravitationShiftFraction,
            strokeWidthPx = strokeWidthPx,
            drawingCache = checkCache
        )
    }
}

private fun DrawScope.drawBox(
    boxColor: Color,
    borderColor: Color,
    radius: Float,
    strokeWidth: Float
) {
    val halfStrokeWidth = strokeWidth / 2.0f
    val stroke = Stroke(strokeWidth)
    val checkboxSize = size.width
    if (boxColor == borderColor) {
        drawRoundRect(
            boxColor,
            size = Size(checkboxSize, checkboxSize),
            cornerRadius = CornerRadius(radius),
            style = Fill
        )
    } else {
        drawRoundRect(
            boxColor,
            topLeft = Offset(strokeWidth, strokeWidth),
            size = Size(checkboxSize - strokeWidth * 2, checkboxSize - strokeWidth * 2),
            cornerRadius = CornerRadius(max(0f, radius - strokeWidth)),
            style = Fill
        )
        drawRoundRect(
            borderColor,
            topLeft = Offset(halfStrokeWidth, halfStrokeWidth),
            size = Size(checkboxSize - strokeWidth, checkboxSize - strokeWidth),
            cornerRadius = CornerRadius(radius - halfStrokeWidth),
            style = stroke
        )
    }
}

private fun DrawScope.drawCheck(
    checkColor: Color,
    checkFraction: Float,
    crossCenterGravitation: Float,
    strokeWidthPx: Float,
    drawingCache: CheckDrawingCache
) {
    val stroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Square)
    val width = size.width
    val checkCrossX = 0.416f
    val checkCrossY = 0.666f
    val leftX = 0.25f
    val leftY = 0.5f
    val rightX = 0.75f
    val rightY = 0.3f

    val gravitatedCrossX = lerp(checkCrossX, 0.5f, crossCenterGravitation)
    val gravitatedCrossY = lerp(checkCrossY, 0.5f, crossCenterGravitation)
    val gravitatedLeftY = lerp(leftY, 0.5f, crossCenterGravitation)
    val gravitatedRightY = lerp(rightY, 0.5f, crossCenterGravitation)

    with(drawingCache) {
        checkPath.reset()
        checkPath.moveTo(width * leftX, width * gravitatedLeftY)
        checkPath.lineTo(width * gravitatedCrossX, width * gravitatedCrossY)
        checkPath.lineTo(width * rightX, width * gravitatedRightY)
        pathMeasure.setPath(checkPath, false)
        pathToDraw.reset()
        pathMeasure.getSegment(
            0f,
            pathMeasure.length * checkFraction,
            pathToDraw,
            true
        )
    }
    drawPath(drawingCache.pathToDraw, checkColor, style = stroke)
}

@Immutable
private class CheckDrawingCache(
    val checkPath: Path = Path(),
    val pathMeasure: PathMeasure = PathMeasure(),
    val pathToDraw: Path = Path()
)

@Composable
private fun checkmarkColor(
    style: CheckboxStyles,
    enabled: Boolean,
    state: ToggleableState
): State<Color> {
    val target = if (state == ToggleableState.Off) {
        style.uncheckedCheckmarkColor
    } else {
        if (enabled) {
            style.checkedCheckmarkColor
        } else {
            style.disabledCheckedCheckmarkColor
        }
    }

    val duration = if (state == ToggleableState.Off) {
        style.boxOutDuration
    } else {
        style.boxInDuration
    }
    return animateColorAsState(target, tween(durationMillis = duration))
}

@Composable
private fun boxColor(
    style: CheckboxStyles,
    enabled: Boolean,
    state: ToggleableState
): State<Color> {
    val target = if (enabled) {
        when (state) {
            ToggleableState.On, ToggleableState.Indeterminate -> style.checkedBoxColor
            ToggleableState.Off -> style.uncheckedBoxColor
        }
    } else {
        when (state) {
            ToggleableState.On -> style.disabledCheckedBoxColor
            ToggleableState.Indeterminate -> style.disabledIndeterminateBoxColor
            ToggleableState.Off -> style.disabledUncheckedBoxColor
        }
    }
    return if (enabled) {
        val duration = if (state == ToggleableState.Off) {
            style.boxOutDuration
        } else {
            style.boxInDuration
        }
        animateColorAsState(target, tween(durationMillis = duration))
    } else {
        rememberUpdatedState(target)
    }
}

@Composable
private fun borderColor(
    style: CheckboxStyles,
    enabled: Boolean,
    state: ToggleableState
): State<Color> {
    val target = if (enabled) {
        when (state) {
            ToggleableState.On, ToggleableState.Indeterminate -> style.checkedBorderColor
            ToggleableState.Off -> style.uncheckedBorderColor
        }
    } else {
        when (state) {
            ToggleableState.Indeterminate -> style.disabledIndeterminateBorderColor
            ToggleableState.On, ToggleableState.Off -> style.disabledBorderColor
        }
    }
    return if (enabled) {
        val duration = if (state == ToggleableState.Off) {
            style.boxOutDuration
        } else {
            style.boxInDuration
        }
        animateColorAsState(target, tween(durationMillis = duration))
    } else {
        rememberUpdatedState(target)
    }
}

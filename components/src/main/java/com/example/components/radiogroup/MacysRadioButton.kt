package com.example.components.radiogroup

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.components.MinimumTouchTargetModifier

@Composable
fun RadioButton(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    style: RadioButtonStyles,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    buttonsSpacedManually: Boolean = false,
    onClick: (() -> Unit)?
) {
    val dotRadius = animateDpAsState(
        targetValue = if (selected) {
            style.radioButtonDotSize / 2
        } else {
            0.dp
        },
        animationSpec = tween(durationMillis = style.radioAnimationDuration)
    )
    val fillColor = fillColor(style = style, enabled = enabled)
    val dotColor = dotColor(style = style, enabled = enabled, selected = selected)
    val strokeColor = strokeColor(style = style, enabled = enabled)

    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = style.radioButtonRippleRadius,
                    color = style.rippleColor
                )
            )
        } else {
            Modifier
        }
    Canvas(
        modifier
            .then(
                if (onClick != null && !buttonsSpacedManually) {
                    val size = LocalViewConfiguration.current.minimumTouchTargetSize
                    MinimumTouchTargetModifier(size)
                } else {
                    Modifier
                }
            )
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .requiredSize(style.radioButtonSize)
    ) {
        val strokeWidth = style.radioStrokeWidth.toPx()
        drawCircle(
            color = fillColor.value,
            radius = style.radioButtonRadius.toPx(),
            style = Fill
        )
        drawCircle(
            color = strokeColor.value,
            radius = style.radioButtonRadius.toPx() - strokeWidth / 2,
            style = Stroke(strokeWidth)
        )
        if (dotRadius.value > 0.dp) {
            drawCircle(
                color = dotColor.value,
                radius = dotRadius.value.toPx(),
                style = Fill
            )
        }
    }
}

@Composable
private fun fillColor(style: RadioButtonStyles, enabled: Boolean): State<Color> {
    val color = if (enabled) {
        style.fillColor
    } else {
        style.disabledFillColor
    }
    return rememberUpdatedState(color)
}

@Composable
private fun strokeColor(style: RadioButtonStyles, enabled: Boolean): State<Color> {
    val color = if (enabled) {
        style.strokeColor
    } else {
        style.disabledStrokeColor
    }
    return rememberUpdatedState(color)
}

@Composable
private fun dotColor(style: RadioButtonStyles, enabled: Boolean, selected: Boolean): State<Color> {
    val color = when {
        !enabled -> style.disabledDotColor
        !selected -> Color.Transparent
        else -> style.dotColor
    }
    return if (enabled) {
        animateColorAsState(color, tween(durationMillis = style.radioAnimationDuration))
    } else {
        rememberUpdatedState(color)
    }
}

package com.example.components.radiogroup

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.components.theme.AppTheme

@Composable
fun radioButtonStyleMacysRedesign() = RadioButtonStyles(
    radioAnimationDuration = 100,
    radioButtonRippleRadius = 20.dp,
    rippleColor = AppTheme.colors.gdGrey2,
    radioButtonSize = 24.dp,
    radioButtonRadius = 12.dp,
    radioButtonDotSize = 14.dp,
    radioStrokeWidth = 1.dp,
    dotColor = AppTheme.colors.gdBlack,
    strokeColor = AppTheme.colors.gdBlack,
    disabledStrokeColor = AppTheme.colors.gdGrey2,
    disabledDotColor = AppTheme.colors.gdGrey2,
    fillColor = Color.Transparent,
    disabledFillColor = AppTheme.colors.gdGrey5,
    textStyle = AppTheme.textAppearance.body,
    textEnabledColor = AppTheme.colors.gdBlack,
    textDisabledColor = AppTheme.colors.gdGrey1
)

data class RadioButtonStyles(
    var radioAnimationDuration: Int,
    var radioButtonRippleRadius: Dp,
    var rippleColor: Color,
    var radioButtonSize: Dp,
    var radioButtonRadius: Dp,
    var radioButtonDotSize: Dp,
    var radioStrokeWidth: Dp,
    var dotColor: Color,
    var strokeColor: Color,
    var disabledStrokeColor: Color,
    var disabledDotColor: Color,
    var fillColor: Color,
    var disabledFillColor: Color,
    var textStyle: TextStyle,
    var textEnabledColor: Color,
    var textDisabledColor: Color
)

package com.example.components.checkbox

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.components.theme.AppTheme

@Composable
fun checkboxStyle() = CheckboxStyles(
    checkedCheckmarkColor = AppTheme.colors.gdBlack,
    disabledCheckedCheckmarkColor = AppTheme.colors.gdGrey2,
    uncheckedCheckmarkColor = Color.Transparent,
    checkedBoxColor = AppTheme.colors.gdWhite,
    uncheckedBoxColor = AppTheme.colors.gdWhite,
    disabledCheckedBoxColor = AppTheme.colors.gdGrey5,
    disabledUncheckedBoxColor = AppTheme.colors.gdGrey5,
    disabledIndeterminateBoxColor = AppTheme.colors.gdGrey5,
    checkedBorderColor = AppTheme.colors.gdBlack,
    uncheckedBorderColor = AppTheme.colors.gdBlack,
    disabledBorderColor = AppTheme.colors.gdGrey2,
    disabledIndeterminateBorderColor = AppTheme.colors.gdGrey2,
    boxOutDuration = 100,
    boxInDuration = 50,
    checkAnimationDuration = 100,
    cornerRadius = 0.dp,
    strokeWidth = 1.dp,
    checkboxSize = 24.dp,
    checkboxDefaultPadding = 2.dp,
    checkboxRippleRadius = 20.dp,
    checkboxRippleColor = AppTheme.colors.gdGrey2,
    textStyle = AppTheme.textAppearance.body,
    textEnabledColor = AppTheme.colors.gdBlack,
    textDisabledColor = AppTheme.colors.gdGrey1
)

data class CheckboxStyles(
    val checkedCheckmarkColor: Color,
    val disabledCheckedCheckmarkColor: Color,
    val uncheckedCheckmarkColor: Color,
    val checkedBoxColor: Color,
    val uncheckedBoxColor: Color,
    val disabledCheckedBoxColor: Color,
    val disabledUncheckedBoxColor: Color,
    val disabledIndeterminateBoxColor: Color,
    val checkedBorderColor: Color,
    val uncheckedBorderColor: Color,
    val disabledBorderColor: Color,
    val disabledIndeterminateBorderColor: Color,
    val boxOutDuration: Int,
    val boxInDuration: Int,
    val checkAnimationDuration: Int,
    val cornerRadius: Dp,
    val strokeWidth: Dp,
    val checkboxSize: Dp,
    val checkboxDefaultPadding: Dp,
    val checkboxRippleRadius: Dp,
    val checkboxRippleColor: Color,
    val textStyle: TextStyle,
    val textEnabledColor: Color,
    val textDisabledColor: Color
)

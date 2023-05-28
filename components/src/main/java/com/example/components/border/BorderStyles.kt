package com.example.components.border

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.components.theme.AppTheme

@Composable
fun grayBorder() =
    BorderStyle(
        background = AppTheme.colors.gdGrey3,
        thickness = AppTheme.dimens.normal_6
    )

@Composable
fun mediumGrayBorder() =
    BorderStyle(
        background = AppTheme.colors.gdGrey2,
        thickness = AppTheme.dimens.normal_6
    )

@Composable
fun darkGrayBorder() =
    BorderStyle(
        background = AppTheme.colors.gdGrey1,
        thickness = AppTheme.dimens.normal_6
    )

@Composable
fun blackBorder() =
    BorderStyle(
        background = AppTheme.colors.gdBlack,
        thickness = AppTheme.dimens.normal_6
    )

@Composable
fun thickBlackBorder() =
    BorderStyle(
        background = AppTheme.colors.gdBlack,
        thickness = AppTheme.dimens.normal_12
    )

@Composable
fun thickGraySpacer() =
    BorderStyle(
        background = AppTheme.colors.gdGrey4,
        thickness = AppTheme.dimens.normal_50
    )

data class BorderStyle(
    val background: Color,
    val width: Dp = 0.dp,
    val thickness: Dp = 0.dp
)

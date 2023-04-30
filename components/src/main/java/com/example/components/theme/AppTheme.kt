package com.example.components.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val textAppearance: GridTextStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val dimens: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}

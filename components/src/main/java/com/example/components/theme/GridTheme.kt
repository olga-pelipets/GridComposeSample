package com.example.components.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun GridTheme(
    colors: AppColors = AppTheme.colors,
    dimensions: AppDimensions = AppTheme.dimens,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
    ) {
        content()
    }
}
internal val LocalColors = staticCompositionLocalOf { gridColors() }
internal val LocalTypography = staticCompositionLocalOf { GridTextStyles() }
internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }

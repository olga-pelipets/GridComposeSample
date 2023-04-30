package com.example.components.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class GridTextStyles(
    private val colors: AppColors = gridColors(),

    val body: TextStyle = TextStyle(
        color = colors.gdBlack,
        fontSize = 14.sp,
        letterSpacing = 0.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    val bodyLarge: TextStyle =
        body.copy(
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Medium
        ),

    val bodySmall: TextStyle =
        body.copy(
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),

    val header1: TextStyle =
        body.copy(
            fontSize = 36.sp,
            lineHeight = 42.sp,
        ),

    val header2: TextStyle =
        body.copy(
            fontSize = 30.sp,
            lineHeight = 37.sp,
        )
)
package com.example.components.border

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.components.Orientation

@Composable
fun Border(
    modifier: Modifier = Modifier,
    startIndent: Dp = 0.dp,
    style: BorderStyle,
    orientation: Orientation = Orientation.HORIZONTAL
) {
    Divider(
        modifier = if (orientation == Orientation.HORIZONTAL) {
            modifier
                .fillMaxWidth()
                .height(style.thickness)
        } else {
            modifier
                .fillMaxHeight()
                .width(style.thickness)
        },
        color = style.background,
        thickness = style.thickness,
        startIndent = startIndent
    )
}

package com.example.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.components.R
import com.example.components.theme.AppTheme

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = AppTheme.dimens.normal_0,
        vertical = AppTheme.dimens.normal_0
    ),
    showChevron: Boolean = true,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) AppTheme.colors.gdGrey1 else AppTheme.colors.gdBlack

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme()) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = interactionSource,
            contentPadding = contentPadding,
            shape = RoundedCornerShape(0.dp),
            border = BorderStroke(
                width = 0.dp,
                color = Color.Transparent
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                disabledBackgroundColor = Color.Transparent,
                contentColor = if (enabled) color else AppTheme.colors.gdGrey2,
                disabledContentColor = AppTheme.colors.gdGrey2
            )
        ) {
            ProvideTextStyle(
                value = AppTheme.textAppearance.bodyLarge.copy(
                    color = if (enabled) color else AppTheme.colors.gdGrey2
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.normal_60),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    content()
                    if (showChevron) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "SecondaryButtonRedesign", showBackground = true)
private fun Preview() {
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .padding(12.dp)
    ) {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            onClick = { }
        ) {
            Text("Test text")
        }
    }
}

internal class NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        draggedAlpha = 0f,
        focusedAlpha = 0f,
        hoveredAlpha = 0f,
        pressedAlpha = 0f
    )
}

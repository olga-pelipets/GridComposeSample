package com.example.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.components.theme.AppTheme

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider {
        Button(
            modifier = modifier.defaultMinSize(
                minWidth = AppTheme.dimens.normal_400,
                minHeight = AppTheme.dimens.normal_325
            ),
            elevation = null,
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(1.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.gdYellow,
                disabledBackgroundColor = AppTheme.colors.gdGrey3,
                contentColor = AppTheme.colors.gdBlack,
                disabledContentColor = AppTheme.colors.gdGrey2
            )
        ) {
            ProvideTextStyle(
                value = AppTheme.textAppearance.bodyLarge.copy(
                    color = if (enabled) AppTheme.colors.gdBlack else AppTheme.colors.gdGrey2
                )
            ) {
                content()
            }
        }
    }
}

@Composable
@Preview(name = "PrimaryButton", showBackground = true)
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        PrimaryButton(
            enabled = true,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.normal_50),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_dialog_map),
                    contentDescription = null
                )
                androidx.compose.material.Text(text = "Primary button disabled with icon")
            }
        }
    }
}

@Composable
@Preview(name = "PrimaryButton", showBackground = true)
private fun Preview2() {
    Box(modifier = Modifier.padding(12.dp)) {
        PrimaryButton(
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.normal_50),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_dialog_map),
                    contentDescription = null
                )
                androidx.compose.material.Text(text = "Primary button disabled with icon")
            }
        }
    }
}

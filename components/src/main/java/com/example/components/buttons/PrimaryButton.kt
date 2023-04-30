package com.example.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.components.theme.AppTheme

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonText: String,
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
            )
        ) {
            Text(
                buttonText,
                style = AppTheme.textAppearance.bodyLarge
            )
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
            buttonText = "Test text",
            onClick = { }
        )
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
            buttonText = "Test text",
            onClick = { }
        )
    }
}

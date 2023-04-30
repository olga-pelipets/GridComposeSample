package com.example.common_ui.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.components.buttons.PrimaryButton
import com.example.components.buttons.SecondaryButton
import com.example.components.theme.AppTheme

@Composable
fun ButtonsScreen(
    viewModel: ButtonsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = AppTheme.dimens.normal_100)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttonPrimaryItem(
            enabled = true,
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.PrimaryButtonClick) },
            text = "Primary Button Enabled"
        )
        buttonPrimaryItem(
            enabled = false,
            onClick = { },
            text = "Primary Button Disabled"
        )
        buttonSecondaryItem(
            enabled = true,
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.SecondaryButtonClick) },
            text = "Secondary Button Enabled"
        )
        buttonSecondaryItem(
            enabled = false,
            onClick = { },
            text = "Secondary Button Disabled"
        )
    }
}

@Composable
private fun buttonPrimaryItem(enabled: Boolean, onClick: () -> Unit, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = AppTheme.dimens.normal_50,
                bottom = AppTheme.dimens.normal_50,
            )
    ) {
        PrimaryButton(
            enabled = enabled,
            onClick = { onClick.invoke() },
            modifier = Modifier.fillMaxWidth(),
            buttonText = text
        )
    }
}

@Composable
private fun buttonSecondaryItem(enabled: Boolean, onClick: () -> Unit, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = AppTheme.dimens.normal_50,
                bottom = AppTheme.dimens.normal_50,
            )
    ) {
        SecondaryButton(
            enabled = enabled,
            onClick = { onClick.invoke() },
            modifier = Modifier.fillMaxWidth(),
            buttonText = text
        )
    }
}

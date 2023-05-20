package com.example.common_ui.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.common.ui.R
import com.example.components.buttons.PrimaryButton
import com.example.components.buttons.SecondaryButton
import com.example.components.buttons.TextButton
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
        horizontalAlignment = Alignment.Start
    ) {
        buttonPrimaryItem(
            enabled = true,
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.PrimaryButtonClick) },
        ) {
            Text(text = "Primary button enabled")
        }
        buttonPrimaryItem(
            enabled = true,
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.PrimaryButtonClick) },
        ) {
            Text(text = "Primary button enabled")
        }
        buttonPrimaryItem(
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
        ) {
            Text(text = "Primary button disabled")
        }
        buttonPrimaryItem(
            enabled = false,
            onClick = { },
        ) {
            Text(text = "Primary button disabled")
        }
        buttonPrimaryItem(
            enabled = true,
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.PrimaryButtonClick) },
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.normal_50),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_example),
                    contentDescription = null
                )
                Text(text = "Primary button enabled with icon")
            }
        }
        buttonPrimaryItem(
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.normal_50),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_example),
                    contentDescription = null
                )
                Text(text = "Primary button disabled with icon")
            }
        }
        buttonSecondaryItem(
            enabled = true,
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.SecondaryButtonClick) },
        ) {
            Text(text = "Secondary button enabled")
        }
        buttonSecondaryItem(
            enabled = true,
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.SecondaryButtonClick) },
        ) {
            Text(text = "Secondary button enabled")
        }
        buttonSecondaryItem(
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
        ) {
            Text(text = "Secondary button disabled")
        }
        buttonSecondaryItem(
            enabled = false,
            onClick = { },
        ) {
            Text(text = "Secondary button disabled")
        }
        buttonSecondaryItem(
            enabled = true,
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.SecondaryButtonClick) },
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.normal_50),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_example),
                    contentDescription = null
                )
                Text(text = "Secondary button enabled with icon")
            }
        }
        buttonSecondaryItem(
            enabled = false,
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.normal_50),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_example),
                    contentDescription = null
                )
                Text(text = "Secondary button disabled with icon")
            }
        }
        buttonTextItem(
            enabled = true,
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.TextButtonClick) },
            showChevron = true
        ) {
            Text(text = "Text button enabled")
        }
        buttonTextItem(
            enabled = true,
            showChevron = false,
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.TextButtonClick) },
        ) {
            Text(text = "Text button enabled")
        }
        buttonTextItem(
            enabled = false,
            showChevron = true,
            onClick = { },
        ) {
            Text(text = "Text button disabled")
        }
        buttonTextItem(
            enabled = false,
            showChevron = false,
            onClick = { viewModel.sendEvent(ButtonsViewModel.Event.TextButtonClick) },
        ) {
            Text(text = "Text button disabled")
        }
    }
}

@Composable
private fun buttonPrimaryItem(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
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
            modifier = modifier,
            content = content
        )
    }
}

@Composable
private fun buttonSecondaryItem(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.padding(
                top = AppTheme.dimens.normal_50,
                bottom = AppTheme.dimens.normal_50,
            )
    ) {
        SecondaryButton(
            enabled = enabled,
            onClick = { onClick.invoke() },
            content = content,
            modifier = modifier
        )
    }
}

@Composable
private fun buttonTextItem(
    enabled: Boolean,
    onClick: () -> Unit,
    showChevron: Boolean,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.padding(
            top = AppTheme.dimens.normal_50,
            bottom = AppTheme.dimens.normal_50,
        )
    ) {
        TextButton(
            enabled = enabled,
            onClick = { onClick.invoke() },
            showChevron = showChevron,
            content = content
        )
    }
}

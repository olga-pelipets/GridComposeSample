package com.example.common_ui.radiobutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.components.Orientation
import com.example.components.radiogroup.RadioGroup
import com.example.components.theme.AppTheme

@Composable
fun RadioButtonScreen(
    viewModel: RadioButtonViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = AppTheme.dimens.normal_100)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.normal_200)
    ) {
        RadioGroup(
            selectedIndex = 0,
            radioGroupTextOptions = listOf("Enabled, option 1", "Enabled, option 2"),
            buttonsSpacing = AppTheme.dimens.normal_50,
            onSelectedChange = {
                viewModel.sendEvent(
                    RadioButtonViewModel.Event.RadioButtonChecked(it)
                )
            }
        )
        RadioGroup(
            selectedIndex = 0,
            radioGroupComposableOptions = listOf("Disabled, option 1", "Disabled, option 2").map {
                { Text(text = it) }
            },
            enabled = false,
            buttonsSpacing = AppTheme.dimens.normal_50
        )
        RadioGroup(
            selectedIndex = 0,
            radioGroupComposableOptions = listOf(
                "Enabled Horizontal 1",
                "Enabled Horizontal 2"
            ).map {
                { Text(text = it) }
            },
            orientation = Orientation.HORIZONTAL,
            buttonsSpacing = AppTheme.dimens.normal_50,
            onSelectedChange = {
                viewModel.sendEvent(
                    RadioButtonViewModel.Event.RadioButtonChecked(it)
                )
            }
        )
        RadioGroup(
            selectedIndex = 0,
            radioGroupTextOptions = listOf("Disabled Horizontal 1", "Disabled Horizontal 2"),
            enabled = false,
            orientation = Orientation.HORIZONTAL,
            buttonsSpacing = AppTheme.dimens.normal_50
        )
    }
}

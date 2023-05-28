package com.example.common_ui.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.components.checkbox.Checkbox
import com.example.components.checkbox.checkboxStyle
import com.example.components.theme.AppTheme

@Composable
fun CheckboxScreen(
    viewModel: CheckboxViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = AppTheme.dimens.normal_100)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            var checked by remember { mutableStateOf(true) }
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    viewModel.sendEvent(
                        if (it) CheckboxViewModel.Event.CheckboxChecked
                        else CheckboxViewModel.Event.CheckboxUnchecked
                    )
                },
                style = checkboxStyle(),
                text = "Enabled"
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            var checked by remember { mutableStateOf(false) }
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    viewModel.sendEvent(
                        if (it) CheckboxViewModel.Event.CheckboxChecked
                        else CheckboxViewModel.Event.CheckboxUnchecked
                    )
                },
                style = checkboxStyle(),
                text = "Enabled"
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                enabled = false,
                onCheckedChange = {},
                style = checkboxStyle(),
                text = "Disabled"
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = true,
                enabled = false,
                onCheckedChange = {},
                style = checkboxStyle(),
                text = "Disabled"
            )
        }
    }
}

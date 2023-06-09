package com.example.common_ui.summary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.common.ui.R
import com.example.components.border.Border
import com.example.components.border.grayBorder
import com.example.components.theme.AppTheme

@Composable
fun SummaryScreen(
    viewModel: SummaryViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = AppTheme.dimens.normal_100)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        summaryItem(R.string.summary_sample_app) {
            viewModel.sendEvent(SummaryViewModel.Event.WeatherAppEvent)
        }
        summaryItem(R.string.summary_borders) {
            viewModel.sendEvent(SummaryViewModel.Event.BordersEvent)
        }
        summaryItem(R.string.summary_buttons) {
            viewModel.sendEvent(SummaryViewModel.Event.ButtonsEvent)
        }
        summaryItem(R.string.summary_checkbox) {
            viewModel.sendEvent(SummaryViewModel.Event.CheckboxEvent)
        }
        summaryItem(R.string.summary_radiobutton) {
            viewModel.sendEvent(SummaryViewModel.Event.RadioButtonEvent)
        }
        summaryItem(R.string.summary_typography) {
            viewModel.sendEvent(SummaryViewModel.Event.TypographyEvent)
        }
    }
}

@Composable
private fun summaryItem(stringId: Int, onClick: () -> Unit) {
    Text(
        text = stringResource(stringId),
        style = AppTheme.textAppearance.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = AppTheme.dimens.normal_50)
            .clickable { onClick.invoke() }
    )
    Border(style = grayBorder())
}

package com.example.common_ui.typography

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.components.theme.AppTheme

@Composable
fun TypographyScreen(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = AppTheme.dimens.normal_100)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Text style - header1",
            style = AppTheme.textAppearance.header1,
            modifier = Modifier.padding(all = AppTheme.dimens.normal_100)
        )
        Text(
            "Text style - header2",
            style = AppTheme.textAppearance.header2,
            modifier = Modifier.padding(all = AppTheme.dimens.normal_100)
        )
        Text(
            "Text style - bodyLarge",
            style = AppTheme.textAppearance.bodyLarge,
            modifier = Modifier.padding(all = AppTheme.dimens.normal_100)
        )
        Text(
            "Text style - body",
            style = AppTheme.textAppearance.body,
            modifier = Modifier.padding(all = AppTheme.dimens.normal_100)
        )
        Text(
            "Text style - bodySmall",
            style = AppTheme.textAppearance.bodySmall,
            modifier = Modifier.padding(all = AppTheme.dimens.normal_100)
        )
    }
}

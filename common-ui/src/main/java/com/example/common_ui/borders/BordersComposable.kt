package com.example.common_ui.borders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.components.border.Border
import com.example.components.border.blackBorder
import com.example.components.border.darkGrayBorder
import com.example.components.border.grayBorder
import com.example.components.border.mediumGrayBorder
import com.example.components.border.thickBlackBorder
import com.example.components.border.thickGraySpacer
import com.example.components.theme.AppTheme

@Composable
fun BordersScreen(
) {
    Column(
        modifier = Modifier
            .padding(
                top = AppTheme.dimens.normal_100,
                start = AppTheme.dimens.normal_100,
                end = AppTheme.dimens.normal_100,
                bottom = AppTheme.dimens.normal_100
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(
            AppTheme.dimens.normal_100
        )
    ) {
        androidx.compose.material.Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Gray Border"
        )

        Border(style = grayBorder())

        androidx.compose.material.Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Medium Gray Border"
        )

        Border(style = mediumGrayBorder())

        androidx.compose.material.Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Dark Gray Border"
        )

        Border(style = darkGrayBorder())

        androidx.compose.material.Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Black Border"
        )

        Border(style = blackBorder())

        androidx.compose.material.Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Thick Black Border"
        )

        Border(style = thickBlackBorder())

        androidx.compose.material.Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Thick Gray Spacer"
        )

        Border(style = thickGraySpacer())
    }
}

package com.example.components.radiogroup

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.example.components.Orientation
import com.example.components.clickableWithoutRipple
import com.example.components.theme.AppTheme

@Composable
fun RadioGroup(
    selectedIndex: Int,
    radioButtonStyle: RadioButtonStyles = radioButtonStyleMacysRedesign(),
    radioGroupTextOptions: List<String>? = null,
    radioGroupComposableOptions: List<(@Composable () -> Unit)>? = null,
    enabled: Boolean = true,
    orientation: Orientation = Orientation.VERTICAL,
    buttonsSpacing: Dp = (-1).dp,
    onSelectedChange: (Int) -> Unit = {}
) {
    var selected by remember { mutableStateOf(selectedIndex) }
    val content = @Composable { index: Int, item: @Composable () -> Unit ->
        val interactionSource = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier
                .then(
                    if (enabled) {
                        Modifier.clickableWithoutRipple(interactionSource) {
                            selected = index
                            onSelectedChange(index)
                        }
                    } else {
                        Modifier
                    }
                ),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                AppTheme.dimens.normal_50
            )
        ) {
            RadioButton(
                selected = (index == selected),
                style = radioButtonStyle,
                enabled = enabled,
                interactionSource = interactionSource,
                buttonsSpacedManually = buttonsSpacing.value >= 0
            ) {
                selected = index
                onSelectedChange(index)
            }
            ProvideTextStyle(value = radioButtonStyle.textStyle) {
                item.invoke()
            }
        }
    }
    val itemsList: List<(@Composable () -> Unit)>? =
        radioGroupComposableOptions ?: radioGroupTextOptions?.map {
            { Text(text = it) }
        }
    if (orientation == Orientation.VERTICAL) {
        Column(
            modifier = Modifier.selectableGroup(),
            verticalArrangement = Arrangement.spacedBy(
                buttonsSpacing.coerceAtLeast(0.dp)
            )
        ) {
            itemsList?.forEachIndexed { index, function -> content(index, function) }
        }
    } else {
        Row(
            modifier = Modifier.selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(
                buttonsSpacing.coerceAtLeast(0.dp)
            )
        ) {
            itemsList?.forEachIndexed { index, function -> content(index, function) }
        }
    }
}

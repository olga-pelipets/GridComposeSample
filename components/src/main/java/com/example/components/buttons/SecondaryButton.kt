package com.example.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.components.theme.AppTheme

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonText: String,
) {
    CompositionLocalProvider {
        OutlinedButton(
            modifier = modifier.defaultMinSize(
                minWidth = AppTheme.dimens.normal_400,
                minHeight = AppTheme.dimens.normal_325
            ),
            elevation = null,
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(1.dp),
            border = BorderStroke(
                width = 1.dp,
                color =  if (enabled) {
                        AppTheme.colors.gdBlack
                    } else {
                        AppTheme.colors.gdGrey2
                    }
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.gdWhite,
                disabledBackgroundColor = AppTheme.colors.gdWhite,
            )
        ) {
            androidx.compose.material3.Text(
                buttonText,
                style = AppTheme.textAppearance.bodyLarge.copy(
                    color = if (enabled) {
                        AppTheme.colors.gdBlack
                    } else {
                        AppTheme.colors.gdGrey2
                    }
                )
            )
        }
    }
}

//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun SecondaryButton(
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    contentPadding: PaddingValues = PaddingValues(
//        horizontal = dimensionResource(
//            id = R.dimen.normal_125
//        ),
//        vertical = 14.dp
//    ),
//    content: @Composable () -> Unit
//) {
//    val color = remember { mutableStateOf(R.color.redesignWhite) }
//
//    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme()) {
//        OutlinedButton(
//            onClick = onClick,
//            modifier = modifier.pointerInteropFilter {
//                when (it.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        color.value = R.color.redesignHover
//                        false
//                    }
//
//                    MotionEvent.ACTION_UP -> {
//                        color.value = R.color.redesignWhite
//                        true
//                    }
//                    else -> {
//                        false
//                    }
//                }
//            },
//            enabled = enabled,
//            contentPadding = contentPadding,
//            shape = RoundedCornerShape(5.dp),
//            border = BorderStroke(
//                width = 1.dp,
//                color = colorResource(
//                    id = if (enabled) {
//                        R.color.redesignBlack
//                    } else {
//                        R.color.redesignGray3
//                    }
//                )
//            ),
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = colorResource(color.value),
//                disabledBackgroundColor = AppTheme.colors.redesignHover
//            )
//        ) {
//            ProvideTextStyle(
//                value = TextAppearanceMacysRedesignBodyMedium().copy(
//                    color = colorResource(
//                        id = if (enabled) {
//                            R.color.redesignBlack
//                        } else {
//                            R.color.redesignGray1
//                        }
//                    )
//                )
//            ) {
//                content()
//            }
//        }
//    }
//}

@Composable
@Preview(name = "SecondaryButton", showBackground = true)
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        SecondaryButton(
            enabled = true,
            modifier = Modifier
                .fillMaxWidth(),
            buttonText = "Test secondary text",
            onClick = { }
        )
    }
}

@Composable
@Preview(name = "SecondaryButton", showBackground = true)
private fun Preview2() {
    Box(modifier = Modifier.padding(12.dp)) {
        SecondaryButton(
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(),
            buttonText = "Test secondary text",
            onClick = { }
        )
    }
}

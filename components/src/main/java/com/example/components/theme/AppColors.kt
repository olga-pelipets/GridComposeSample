package com.example.components.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    gdOrange: Color,
    gdYellow: Color,
    gdGreen: Color,
    gdLightGreen: Color,
    gdRed: Color,
    gdBlack: Color,
    gdMainBlack: Color,
    gdGrey1: Color,
    gdGrey2: Color,
    gdGrey3: Color,
    gdGrey4: Color,
    gdGrey5: Color,
    gdWhite: Color,
) {

    var gdOrange by mutableStateOf(gdOrange)
        private set
    var gdYellow by mutableStateOf(gdYellow)
        private set
    var gdGreen by mutableStateOf(gdGreen)
        private set
    var gdLightGreen by mutableStateOf(gdLightGreen)
        private set
    var gdRed by mutableStateOf(gdRed)
        private set
    var gdBlack by mutableStateOf(gdBlack)
        private set
    var gdMainBlack by mutableStateOf(gdMainBlack)
        private set
    var gdWhite by mutableStateOf(gdWhite)
        private set
    var gdGrey1 by mutableStateOf(gdGrey1)
        private set
    var gdGrey2 by mutableStateOf(gdGrey2)
        private set
    var gdGrey3 by mutableStateOf(gdGrey3)
        private set
    var gdGrey4 by mutableStateOf(gdGrey4)
        private set
    var gdGrey5 by mutableStateOf(gdGrey5)
        private set
    fun copy(
        gdOrange: Color = this.gdOrange,
        gdYellow: Color = this.gdYellow,
        gdGreen: Color = this.gdGreen,
        gdLightGreen: Color = this.gdLightGreen,
        gdRed: Color = this.gdRed,
        gdBlack: Color = this.gdBlack,
        gdMainBlack: Color = this.gdMainBlack,
        gdGrey1: Color = this.gdGrey1,
        gdGrey2: Color = this.gdGrey2,
        gdGrey3: Color = this.gdGrey3,
        gdGrey4: Color = this.gdGrey4,
        gdGrey5: Color = this.gdGrey5,
        gdWhite: Color = this.gdWhite,
    ) = AppColors(
        gdOrange = gdOrange,
        gdYellow = gdYellow,
        gdGreen = gdGreen,
        gdLightGreen = gdLightGreen,
        gdRed = gdRed,
        gdBlack = gdBlack,
        gdMainBlack = gdMainBlack,
        gdGrey1 = gdGrey1,
        gdGrey2 = gdGrey2,
        gdGrey3 = gdGrey3,
        gdGrey4 = gdGrey4,
        gdGrey5 = gdGrey5,
        gdWhite = gdWhite,
    )

    fun updateColorsFrom(other: AppColors) {
        gdOrange = other.gdOrange
        gdYellow = other.gdYellow
        gdGreen = other.gdGreen
        gdLightGreen = other.gdLightGreen
        gdRed = other.gdRed
        gdBlack = other.gdBlack
        gdMainBlack = other.gdMainBlack
        gdGrey1 = other.gdGrey1
        gdGrey2 = other.gdGrey2
        gdGrey3 = other.gdGrey3
        gdGrey4 = other.gdGrey4
        gdGrey5 = other.gdGrey5
        gdWhite = other.gdWhite
    }
}

fun gridColors() = AppColors(
    gdOrange = Color(0xFFFF8700),
    gdYellow = Color(0xFFFFB800),
    gdGreen = Color(0xFF34A853),
    gdLightGreen = Color(0xFFF0FCEA),
    gdRed = Color(0xFFD21C1C),
    gdBlack = Color(0xFF000000),
    gdMainBlack = Color(0xFF15141F),
    gdGrey1 = Color(0xFF4A4A4A),
    gdGrey2 = Color(0xFF5F6369),
    gdGrey3 = Color(0xFFB2B2B2),
    gdGrey4 = Color(0xFFF2F2F2),
    gdGrey5 = Color(0xFFF8F7F5),
    gdWhite = Color(0xFFFFFFFF),
)

package com.example.wowinfo.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class Faction(
    @StringRes val name: Int,
    val color: Color?,
    @DrawableRes val logo: Int,
    val races: List<Race>,
)

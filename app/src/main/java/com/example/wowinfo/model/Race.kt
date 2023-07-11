package com.example.wowinfo.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Race(
    @StringRes val name: Int,
    val faction: Faction,
    @DrawableRes val crest: Int,
    @StringRes val description: Int,
    @DrawableRes val wallpaper: Int,
)

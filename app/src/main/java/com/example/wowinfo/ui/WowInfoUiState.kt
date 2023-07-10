package com.example.wowinfo.ui

import com.example.wowinfo.R
import com.example.wowinfo.data.RaceList
import com.example.wowinfo.model.Faction

data class WowInfoUiState(
    val currentFaction: Faction = Faction(
        name = R.string.error,
        color = null,
        logo = R.drawable.ic_launcher_background,
        races = RaceList.neutralRaces,
    ),
)

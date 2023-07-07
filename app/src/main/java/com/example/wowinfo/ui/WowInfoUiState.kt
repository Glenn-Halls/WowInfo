package com.example.wowinfo.ui

import com.example.wowinfo.R
import com.example.wowinfo.model.Faction

data class WowInfoUiState(
    val currentFaction: Faction = Faction(
        R.string.error, null, R.drawable.ic_launcher_background
    ),
)

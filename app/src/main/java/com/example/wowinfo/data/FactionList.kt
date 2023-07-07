package com.example.wowinfo.data

import com.example.wowinfo.R
import com.example.wowinfo.model.Faction
import com.example.wowinfo.ui.theme.warcraft_alliance
import com.example.wowinfo.ui.theme.warcraft_horde

object FactionList {

    val Factions = listOf<Faction>(
        Faction(
            name = R.string.alliance,
            color = warcraft_alliance,
            logo = R.drawable.alliance_logo
        ),
        Faction(
            name = R.string.neutral,
            color = null,
            logo = R.drawable.alliance_horde_logo
        ),
        Faction(
            name = R.string.horde,
            color = warcraft_horde,
            logo = R.drawable.horde_logo
        )
    )
}

package com.example.wowinfo.ui

import FactionList
import RaceList
import androidx.lifecycle.ViewModel
import com.example.wowinfo.R
import com.example.wowinfo.model.Faction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WowInfoViewModel : ViewModel() {

    // Create observable state holder
    private val _uiState = MutableStateFlow(WowInfoUiState())
    // Create accessor to state values
    val uiState: StateFlow<WowInfoUiState> = _uiState
    // List of available factions
    val factionList = FactionList.factions


    // Initialise ViewModel to default values
    init { initialiseUiState() }

    private fun initialiseUiState() {
        _uiState.value =
            WowInfoUiState(
                Faction(
                    name = R.string.neutral,
                    color = null,
                    logo = R.drawable.alliance_horde_logo,
                ),
                RaceList.neutralRaces
            )
    }

    // Setter method to update selected Faction
    fun updateCurrentFaction(newFaction: Faction) {
        _uiState.update {
            it.copy(
                currentFaction = newFaction,
                raceList = when (newFaction.name) {
                    R.string.alliance -> RaceList.allianceRaces
                    R.string.neutral -> RaceList.neutralRaces
                    R.string.horde -> RaceList.hordeRaces
                    else -> RaceList.neutralRaces
                }
            )
        }
    }

}

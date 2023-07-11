package com.example.wowinfo.ui

import FactionList
import com.example.wowinfo.data.RaceList
import androidx.lifecycle.ViewModel
import com.example.wowinfo.R
import com.example.wowinfo.model.Faction
import com.example.wowinfo.model.Race
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WowInfoViewModel : ViewModel() {

    // Create observable state holder
    private val _uiState = MutableStateFlow(WowInfoUiState(currentRace = null))
    // Create accessor to state values
    val uiState: StateFlow<WowInfoUiState> = _uiState
    // List of available factions
    val factionList = FactionList.factions
    // Empty Race selection
    val EmptyRace = Race(
        name = R.string.empty,
        faction = RaceList.neutral,
        crest = R.drawable.crest_horde_vulpera,
        description = R.string.empty,
        wallpaper = R.drawable.transparent_picture
    )


    // Initialise ViewModel to default values
    init { initialiseUiState() }

    private fun initialiseUiState() {
        _uiState.value =
            WowInfoUiState(
                currentFaction = Faction(
                    name = R.string.neutral,
                    color = null,
                    logo = R.drawable.alliance_horde_logo,
                ),
                raceList = RaceList.neutralRaces,
                currentRace = null,
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

    fun updateCurrentRace(newRace: Race) {
        _uiState.update {
            it.copy(
                currentRace = newRace,
                isShowingDetail = true
            )
        }
    }

    fun resetRace() {
        _uiState.update {
            it.copy(
                currentRace = null,
                isShowingDetail = false
            )
        }
    }

}

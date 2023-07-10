package com.example.wowinfo.ui

import androidx.lifecycle.ViewModel
import com.example.wowinfo.R
import com.example.wowinfo.data.RaceList
import com.example.wowinfo.model.Faction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WowInfoViewModel : ViewModel() {

    // Create observable state holder
    private val _uiState = MutableStateFlow(WowInfoUiState())
    // Create accessor to state values
    val uiState: StateFlow<WowInfoUiState> = _uiState

    // Initialise ViewModel to default values
    init { initialiseUiState() }

    private fun initialiseUiState() {
        _uiState.value =
            WowInfoUiState(
                Faction(
                    name = R.string.neutral,
                    color = null,
                    logo = R.drawable.alliance_horde_logo,
                    races = RaceList.neutralRaces
                )
            )
    }

    // Setter method to update selected Faction
    fun updateCurrentFaction(newFaction: Faction) {
        _uiState.update {
            it.copy(
                currentFaction = newFaction
            )
        }
    }

}

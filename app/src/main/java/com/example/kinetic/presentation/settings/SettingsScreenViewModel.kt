package com.example.kinetic.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinetic.presentation.uievent.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SettingsScreenViewModel: ViewModel() {
    var dialogState by mutableStateOf(false)
    var darkMode by mutableStateOf(false)
    var lightMode by mutableStateOf(false)
    var systemSettings by mutableStateOf(true)
    var materialYou by mutableStateOf(false)

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    var uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(settingsScreenEvents: SettingsScreenEvents){
        when(settingsScreenEvents){
            is SettingsScreenEvents.OnDarkModeSelected -> {
                darkMode = settingsScreenEvents.selected
                lightMode = false
                systemSettings = false
                materialYou = false
            }
            is SettingsScreenEvents.OnLightModeYouSelected -> {
                lightMode = settingsScreenEvents.selected
                darkMode = false
                systemSettings = false
                materialYou = false
            }
            is SettingsScreenEvents.OnMaterialYouSelected -> {
                materialYou = settingsScreenEvents.selected
                lightMode = false
                darkMode = false
                systemSettings = false
            }
            is SettingsScreenEvents.OnUseSystemSettingsSelected -> {
                systemSettings = settingsScreenEvents.selected
                lightMode = false
                darkMode = false
                materialYou = false
            }
            is SettingsScreenEvents.OnPopBackStack -> {
                viewModelScope.launch { _uiEvent.emit(UiEvent.PopBackStack) }
            }
            is SettingsScreenEvents.OnDialogStateChanged -> {
                dialogState = settingsScreenEvents.dialogState
            }
        }
    }
}
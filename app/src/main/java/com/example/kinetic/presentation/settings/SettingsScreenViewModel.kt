package com.example.kinetic.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinetic.presentation.uievent.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
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
                settingsScreenEvents.selected = darkMode
            }
            is SettingsScreenEvents.OnLightModeYouSelected -> {
                settingsScreenEvents.selected = lightMode
            }
            is SettingsScreenEvents.OnMaterialYouSelected -> {
                settingsScreenEvents.selected = materialYou
            }
            is SettingsScreenEvents.OnUseSystemSettingsSelected -> {
                settingsScreenEvents.selected = systemSettings
            }
            is SettingsScreenEvents.OnPopBackStack -> {
                viewModelScope.launch {
                    _uiEvent.emit(UiEvent.PopBackStack)
                }
            }
            is SettingsScreenEvents.OnDialogStateChanged -> {
                settingsScreenEvents.dialogState = dialogState
            }
        }
    }
}
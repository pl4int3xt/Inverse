package com.example.kinetic.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SettingsScreenViewModel: ViewModel() {
    var darkMode by mutableStateOf(false)
    var lightMode by mutableStateOf(false)
    var systemSettings by mutableStateOf(true)
    var materialYou by mutableStateOf(false)

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
        }
    }
}
package com.example.kinetic.presentation.settings

sealed class SettingsScreenEvents {
    data class OnMaterialYouSelected(var selected: Boolean): SettingsScreenEvents()
    data class OnUseSystemSettingsSelected(var selected: Boolean): SettingsScreenEvents()
    data class OnDarkModeSelected(var selected: Boolean): SettingsScreenEvents()
    data class OnLightModeYouSelected(var selected: Boolean): SettingsScreenEvents()
}
package com.example.kinetic.presentation.navigation

sealed class AppEvents {
    data class ShowBottomBar(val showBottomBar: Boolean): AppEvents()
}
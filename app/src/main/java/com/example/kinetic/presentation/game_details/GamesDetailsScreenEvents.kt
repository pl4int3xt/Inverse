package com.example.kinetic.presentation.game_details

sealed class GamesDetailsScreenEvents {
    object OnCancelClicked: GamesDetailsScreenEvents()
    object OnSearchClicked: GamesDetailsScreenEvents()
}
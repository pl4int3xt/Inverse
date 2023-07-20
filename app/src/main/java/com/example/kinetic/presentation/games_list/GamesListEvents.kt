package com.example.kinetic.presentation.games_list

sealed class GamesListEvents {
    data class OnGameClicked(val id: String): GamesListEvents()
    object OnPopBackStack: GamesListEvents()
}
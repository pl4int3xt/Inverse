package com.example.kinetic.presentation.games_list

data class GamesListScreenState(
    val isLoading: Boolean = false,
    val isNextLoading: Boolean = false,
    val message: String = ""
)
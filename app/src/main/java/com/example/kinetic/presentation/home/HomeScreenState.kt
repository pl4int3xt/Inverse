package com.example.kinetic.presentation.home

import com.example.kinetic.domain.model.GameModel

data class HomeScreenState(
    val isLoading: Boolean = false,
    var games: List<GameModel> = emptyList(),
    val isNextLoading: Boolean = false,
    val message: String = ""
)
package com.example.kinetic.presentation.home

import com.example.kinetic.domain.model.GameModel

data class HomeScreenState(
    val isLoading: Boolean = false,
    val nextLoading: Boolean = false,
    val games: List<GameModel> = emptyList(),
    val message: String = ""
)
package com.example.kinetic.presentation.search

import com.example.kinetic.domain.model.GameModel

data class SearchScreenState(
    val isLoading: Boolean = false,
    val nextLoading: Boolean = false,
    val games: List<GameModel> = emptyList(),
    val message: String = ""
)
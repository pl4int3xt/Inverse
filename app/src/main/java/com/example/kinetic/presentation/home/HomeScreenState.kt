package com.example.kinetic.presentation.home

import com.example.kinetic.data.remote.dto.GameDto

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isNextLoading: Boolean = false,
    val message: String = "",
    val games: List<GameDto>
)
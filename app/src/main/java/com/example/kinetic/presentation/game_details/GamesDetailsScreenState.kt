package com.example.kinetic.presentation.game_details

import com.example.kinetic.domain.model.GameDetailsModel

data class GamesDetailsScreenState(
    val isLoading: Boolean = false,
    val gameDetails: GameDetailsModel? = null,
    val message: String = ""
)
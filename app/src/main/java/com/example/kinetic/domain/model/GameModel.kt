package com.example.kinetic.domain.model

data class GameModel(
    val id: Int,
    val name: String? = "",
    val image: String? = "",
    val rating: Double? = 0.0,
    val metacritic: Int? = 0
)
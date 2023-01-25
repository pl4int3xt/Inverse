package com.example.kinetic.domain.model

import com.example.kinetic.data.remote.dto.EsrbRating

data class GameModel(
    val id: Int,
    val name: String,
    val image: String,
    val rating: Double,
    val esrb_rating: EsrbRating
)
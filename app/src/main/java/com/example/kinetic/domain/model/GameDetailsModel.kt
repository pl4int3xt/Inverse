package com.example.kinetic.domain.model

data class GameDetailsModel(
    val name: String,
    val metacritic: Int,
    val backgroundImage: String,
    val backgroundImageAdditional: String,
    val rating: Double,
    val ratings: String,
    val platforms: List<String?>,
    val pcRequirements: String,
    val genres: List<String?>,
    val publisher: String,
    val publisherImage: String,
    val esrbRating: String,
    val description: String
)
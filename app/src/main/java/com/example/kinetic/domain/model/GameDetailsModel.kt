package com.example.kinetic.domain.model

data class GameDetailsModel(
    val name: String? = "",
    val metacritic: Int? = 0,
    val backgroundImage: String? = "",
    val backgroundImageAdditional: String? = "",
    val rating: Double? = 0.0,
    val platforms: List<String?>,
    val pcRequirements: String?,
    val genres: List<String?>,
    val publisher: String? = "",
    val publisherImage: String? = "",
    val esrbRating: String? = "",
    val description: String? = ""
)
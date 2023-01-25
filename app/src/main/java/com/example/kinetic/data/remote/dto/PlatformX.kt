package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlatformX(
    val platform: PlatformXX,
    val released_at: String? = "",
    val requirements_en: RequirementsEn,
    val requirements_ru: RequirementsRu
)
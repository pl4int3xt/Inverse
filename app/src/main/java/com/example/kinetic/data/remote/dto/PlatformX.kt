package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlatformX(
    val platform: PlatformXX? = PlatformXX(),
    val released_at: String? = "",
    val requirements_en: RequirementsEn? = RequirementsEn(),
    val requirements_ru: RequirementsRu? = RequirementsRu()
)
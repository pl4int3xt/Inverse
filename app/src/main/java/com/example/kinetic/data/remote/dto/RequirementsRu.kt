package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RequirementsRu(
    val minimum: String? = "",
    val recommended: String? = "",
)
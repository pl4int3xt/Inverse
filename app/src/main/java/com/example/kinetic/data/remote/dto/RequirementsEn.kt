package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RequirementsEn(
    val minimum: String? = "",
    val recommended: String? = ""
)
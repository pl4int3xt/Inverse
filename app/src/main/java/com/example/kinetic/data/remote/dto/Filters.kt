package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Filters(
    val years: List<Year>? = emptyList()
)
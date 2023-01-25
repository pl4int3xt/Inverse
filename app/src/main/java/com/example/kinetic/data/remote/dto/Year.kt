package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Year(
    val count: Int? = 0,
    val decade: Int? = 0,
    val filter: String? = "",
    val from: Int? = 0,
    val nofollow: Boolean? = false,
    val to: Int? = 0,
    val years: List<YearX>
)
package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class YearX(
    val count: Int,
    val nofollow: Boolean,
    val year: Int
)
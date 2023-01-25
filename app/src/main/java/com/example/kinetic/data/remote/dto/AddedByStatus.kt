package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddedByStatus(
    val beaten: Int,
    val dropped: Int,
    val owned: Int,
    val playing: Int,
    val toplay: Int,
    val yet: Int
)
package com.example.kinetic.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddedByStatusX(
    val beaten: Int? = 0,
    val dropped: Int? = 0,
    val owned: Int? = 0,
    val playing: Int? = 0,
    val toplay: Int? = 0,
    val yet: Int? = 0,
)
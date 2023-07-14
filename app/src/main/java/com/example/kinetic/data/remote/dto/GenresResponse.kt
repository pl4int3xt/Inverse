package com.example.kinetic.data.remote.dto

data class GenresResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<GenreDto>
)
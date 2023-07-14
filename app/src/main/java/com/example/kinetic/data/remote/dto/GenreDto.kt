package com.example.kinetic.data.remote.dto

import com.example.kinetic.domain.model.GenreModel

data class GenreDto(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)

fun GenreDto.toGenreModel(): GenreModel{
    return GenreModel(
        gamesCount = games_count,
        id = id,
        imageBackground = image_background,
        name = name,
        slug = slug
    )
}
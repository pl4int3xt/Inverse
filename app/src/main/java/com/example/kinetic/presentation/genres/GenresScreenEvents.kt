package com.example.kinetic.presentation.genres

sealed class GenresScreenEvents {
    data class OnGenreClicked(val genre: String): GenresScreenEvents()
}
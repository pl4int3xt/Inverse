package com.example.kinetic.presentation.search

data class SearchScreenState(
    val isLoading: Boolean = false,
    val isNextLoading: Boolean = false,
    val message: String = ""
)
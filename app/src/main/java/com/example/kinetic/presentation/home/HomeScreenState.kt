package com.example.kinetic.presentation.home

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isNextLoading: Boolean = false,
    val message: String = ""
)
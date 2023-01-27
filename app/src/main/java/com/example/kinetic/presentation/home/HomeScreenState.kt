package com.example.kinetic.presentation.home

import androidx.compose.runtime.MutableState
import com.example.kinetic.domain.model.GameModel

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isNextLoading: Boolean = false,
    val message: String = ""
)
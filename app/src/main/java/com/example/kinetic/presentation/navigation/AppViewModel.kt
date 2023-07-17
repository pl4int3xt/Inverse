package com.example.kinetic.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    var showBottomBar by mutableStateOf(true)
    fun onEvent(appEvents: AppEvents){
        when(appEvents){
            is AppEvents.ShowBottomBar -> {

            }
        }
    }
}
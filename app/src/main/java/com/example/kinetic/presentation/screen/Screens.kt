package com.example.kinetic.presentation.screen

sealed class Screens (val route: String){
    object WelcomeScreen: Screens("welcome_screen")
    object HomeScreen: Screens("home_screen")
    object GameDetailsScreen: Screens("game_details_screen")
    object SearchScreen: Screens("search_screen")
}
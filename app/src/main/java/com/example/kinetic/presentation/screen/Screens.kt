package com.example.kinetic.presentation.screen

sealed class Screens (val route: String){
    object WelcomeScreen: Screens("welcome_screen")
    object GenreScreen: Screens("genre_screen")
    object HomeScreen: Screens("home_screen")
    object GameDetailsScreen: Screens("game_details_screen")
    object SearchScreen: Screens("search_screen")
    object SettingsScreen: Screens("settings_screen")
}
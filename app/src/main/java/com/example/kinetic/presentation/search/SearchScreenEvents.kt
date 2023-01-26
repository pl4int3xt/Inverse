package com.example.kinetic.presentation.search

sealed class SearchScreenEvents {
    object OnSearchClicked: SearchScreenEvents()
    object OnBackClicked: SearchScreenEvents()
    data class OnSearchQueryChanged(val search: String): SearchScreenEvents()
}
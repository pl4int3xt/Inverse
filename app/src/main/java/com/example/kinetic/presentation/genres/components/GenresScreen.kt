package com.example.kinetic.presentation.genres.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kinetic.presentation.genres.GenresScreenViewModel
import com.example.kinetic.presentation.uievent.UiEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenresScreen(
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: GenresScreenViewModel = hiltViewModel()
) {
    val genres = viewModel.pagingFlow.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true){
        viewModel.uiEvents.collectLatest {  event ->
            when(event){
                is UiEvent.OnNavigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }

    Scaffold {

    }
}
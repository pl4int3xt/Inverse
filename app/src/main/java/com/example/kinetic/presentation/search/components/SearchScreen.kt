package com.example.kinetic.presentation.search.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.kinetic.presentation.home.components.GameCard
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.shared.SearchBar
import com.example.kinetic.presentation.search.SearchScreenEvents
import com.example.kinetic.presentation.search.SearchScreenViewModel
import com.example.kinetic.presentation.uievent.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    onPopBackStack: () -> Unit,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(key1 = true, context){
        viewModel.uiEvent.collect {event ->
            when(event){
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SearchBar(
                scrollBehavior = scrollBehavior,
                text = viewModel.searchQuery,
                onTextChange = { viewModel.onEvent(SearchScreenEvents.OnSearchQueryChanged(it))},
                onCloseClicked = { viewModel.onEvent(SearchScreenEvents.OnBackClicked) },
                onSearchClicked = { viewModel.onEvent(SearchScreenEvents.OnSearchClicked)}
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                item {
                    Column(modifier = Modifier.fillMaxWidth()
                        .height(50.dp)
                    ) {

                    }
                }
                item {
                    Column(modifier = Modifier.fillMaxWidth()
                        .height(50.dp)
                    ) {

                    }
                }
                items(state.games.size){ i ->
                    GameCard(
                        name = state.games[i].name,
                        image = state.games[i].image,
                        rating = state.games[i].rating,
                        onclick = {
                            navHostController.navigate(
                                Screens.GameDetailsScreen.route + "/${state.games[i].id}")
                        }
                    )
                }
            }
            if (state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}
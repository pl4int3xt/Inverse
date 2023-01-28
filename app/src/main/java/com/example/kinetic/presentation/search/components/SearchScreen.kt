package com.example.kinetic.presentation.search.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kinetic.R
import com.example.kinetic.presentation.home.components.GameCard
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.search.SearchScreenEvents
import com.example.kinetic.presentation.search.SearchScreenViewModel
import com.example.kinetic.presentation.shared.SearchBar
import com.example.kinetic.presentation.uievent.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    onPopBackStack: () -> Unit,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val PAGE_SIZE = 20
    val page = viewModel.page.value
    val context = LocalContext.current
    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val games = viewModel.currentGames.value

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
                onSearchClicked = { viewModel.onEvent(SearchScreenEvents.OnSearchClicked)},
                onResetSearchState = { viewModel.resetSearchState() }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            if(state.isLoading){
                val lottieCompositionSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
                    R.raw.gaming))
                LottieAnimation(
                    composition = lottieCompositionSpec,
                    iterations = Int.MAX_VALUE,
                    alignment = Alignment.Center
                )
            } else if (state.message.isNotEmpty()){
                Button(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                    ,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    contentPadding = PaddingValues(0.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 5.dp,
                        focusedElevation = 5.dp,
                        hoveredElevation = 5.dp,
                    ),
                    shape = CircleShape
                    ,
                    onClick = { viewModel.searchGame() }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.tertiary,
                        imageVector = Icons.Default.Refresh, contentDescription = "refresh icon")
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    item {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                        ) {

                        }
                    }
                    item {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                        ) {

                        }
                    }
                    itemsIndexed(games){ i , game->
                        viewModel.onChangeGamesScrollPosition(i)
                        if ((i + 1) >= (page * PAGE_SIZE) && !state.isNextLoading) {
                            viewModel.nextPage()
                        }
                        GameCard(
                            name = game.name?:"",
                            image = game.image?:"",
                            rating = game.rating?:0.0,
                            onclick = {
                                navHostController.navigate(
                                    Screens.GameDetailsScreen.route + "/${game.id}")
                            }
                        )
                    }
                    item {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
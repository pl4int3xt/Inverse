package com.example.kinetic.presentation.home.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kinetic.presentation.home.HomeScreenEvents
import com.example.kinetic.presentation.home.HomeScreenViewModel
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.shared.MainTopAppBar
import com.example.kinetic.presentation.uievent.UiEvent
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val games = viewModel.pagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(key1 = games.loadState){
        if (games.loadState.refresh is LoadState.Error){
            Toast.makeText(
                context,
                "Error: " + (games.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    LaunchedEffect(key1 = true, context){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiEvent.OnNavigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = Icons.Filled.Settings,
                onClickNavigation = {
                  viewModel.onEvent(HomeScreenEvents.OnSettingsCLicked)
                },
                onClickAction = { viewModel.onEvent(HomeScreenEvents.OnSearchClicked)},
                actions = Icons.Default.Search
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            when(games.loadState.refresh){
                is LoadState.Loading -> {
                    val lottieCompositionSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
                        com.example.kinetic.R.raw.gaming))
                    LottieAnimation(
                        composition = lottieCompositionSpec,
                        iterations = Int.MAX_VALUE,
                        alignment = Alignment.Center
                    )
                }
                is LoadState.Error -> {
                    Button(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                        ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 5.dp,
                            pressedElevation = 5.dp,
                            focusedElevation = 5.dp,
                            hoveredElevation = 5.dp,
                        ),
                        shape = CircleShape,
                        onClick = { games.refresh() }) {
                        Icon(
                            tint = MaterialTheme.colorScheme.tertiary,
                            imageVector = Icons.Default.Refresh, contentDescription = "refresh icon"
                        )
                    }
                }
                else -> {
                    Box {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LazyColumn(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                item {
                                    Column(
                                        modifier = Modifier.height(100.dp)
                                    ) {

                                    }
                                }
                                item {
                                    Column(
                                        modifier = Modifier.padding(5.dp),
                                    ) {
                                        Text(
                                            text = "Republic of",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 35.sp,
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                        Text(
                                            text = "Gamers",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 35.sp,
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                }
                                items(games) { game ->
                                    if (game != null) {
                                        GameCard(
                                            name = game.name ?: "",
                                            image = game.image ?: "",
                                            rating = game.rating ?: 0.0,
                                            metacritic = game.metacritic?:0,
                                            onclick = {
                                                navHostController.navigate(
                                                    Screens.GameDetailsScreen.route + "/${game.id}"
                                                )
                                            }
                                        )
                                    }
                                }
                                if (games.loadState.append is LoadState.Loading) {
                                    item {
                                        CircularProgressIndicator()
                                    }
                                }
                                if (games.loadState.append is LoadState.Error){
                                    item {
                                        (games.loadState.append as LoadState.Error).error.message?.let { it1 -> Text(text = it1) }
                                    }
                                }
                                item {
                                    Spacer(modifier = Modifier.height(100.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
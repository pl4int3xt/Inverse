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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kinetic.presentation.home.HomeScreenEvents
import com.example.kinetic.presentation.home.HomeScreenViewModel
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.shared.MainTopAppBar
import com.example.kinetic.presentation.uievent.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onThemeChange: () -> Unit,
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val PAGE_SIZE = 20
    val page = viewModel.page.value
    val context = LocalContext.current
    val state = viewModel.state.value
    val games = viewModel.currentGames.value

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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            MainTopAppBar(
                navigationIcon = if (viewModel.darkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                onClickNavigation = {
                    onThemeChange()
                    viewModel.darkTheme = !viewModel.darkTheme },
                onClickAction = { viewModel.onEvent(HomeScreenEvents.OnSearchClicked)},
                actions = Icons.Default.Search
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            if(state.isLoading){
                val lottieCompositionSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
                    com.example.kinetic.R.raw.gaming))
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
                    onClick = { viewModel.getGames() }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.tertiary,
                        imageVector = Icons.Default.Refresh, contentDescription = "refresh icon")
                }
            } else {
                Box {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn() {
                            item {
                                Column(
                                    modifier = Modifier.height(150.dp)
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
                                        text = "Gamer",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 35.sp,
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                }
                            }
                            itemsIndexed(games) { i, game ->
                                viewModel.onChangeGamesScrollPosition(i)
                                if ((i + 1) >= (page * PAGE_SIZE) && !state.isNextLoading) {
                                    viewModel.nextPage()
                                }
                                GameCard(
                                    name = game.name ?: "",
                                    image = game.image ?: "",
                                    rating = game.rating ?: 0.0,
                                    onclick = {
                                        navHostController.navigate(
                                            Screens.GameDetailsScreen.route + "/${game.id}"
                                        )
                                    }
                                )
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
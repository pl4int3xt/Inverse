package com.example.kinetic.presentation.game_details.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kinetic.R
import com.example.kinetic.presentation.game_details.GamesDetailsScreenEvents
import com.example.kinetic.presentation.game_details.GamesDetailsScreenViewModel
import com.example.kinetic.presentation.shared.MainTopAppBar
import com.example.kinetic.presentation.uievent.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GameDetailsScreen(
    onPopBackStack: () -> Unit,
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: GamesDetailsScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(key1 = true){
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
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainTopAppBar(
                scrollBehavior = scrollBehavior,
                title = "",
                navigationIcon = Icons.Default.ArrowBack,
                onClickNavigation = { viewModel.onEvent(GamesDetailsScreenEvents.OnCancelClicked) },
                onClickAction = { viewModel.onEvent(GamesDetailsScreenEvents.OnSearchClicked)},
                actions = Icons.Default.Search
            )
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            if(state.isLoading){
                val lottieCompositionSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
                    R.raw.gaming))
                LottieAnimation(
                    composition = lottieCompositionSpec,
                    iterations = Int.MAX_VALUE,
                    alignment = Alignment.Center
                )
            } else if (state.message.isNotEmpty()){
                IconButton(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.05f
                            ),
                            shape = CircleShape
                        )
                    ,
                    onClick = { viewModel.getGameDetails() }
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.tertiary,
                        imageVector = Icons.Default.Refresh, contentDescription = "refresh icon")
                }
            } else {
                Box {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .graphicsLayer {
                                    translationY = 0.4f * scrollState.value
                                }
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .height(600.dp)
                                    .fillMaxWidth()
                                    .clickable { }
                                ,
                                contentScale = ContentScale.Crop,
                                model = state.gameDetails?.backgroundImage,
                                contentDescription = "image"
                            )
                        }
                        Column(
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.background
                            )
                        ){
                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.3f)
                                    .background(
                                        color = MaterialTheme.colorScheme.background
                                    )
                            ) {
                                state.gameDetails?.let { it1 ->
                                    it1.name?.let { it2 ->
                                        Text(
                                            text = it2,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 30.sp,
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Row {
                                    state.gameDetails?.esrbRating?.let {
                                        when (it) {
                                            "Mature" -> {
                                                EsrbRating(
                                                    image = R.drawable.mature,
                                                    description = "mature"
                                                )
                                            }

                                            "Teen" -> {
                                                EsrbRating(
                                                    image = R.drawable.teen,
                                                    description = "mature"
                                                )
                                            }

                                            "Everyone" -> {
                                                EsrbRating(
                                                    image = R.drawable.everyone,
                                                    description = "mature"
                                                )
                                            }

                                            else -> {
                                                EsrbRating(name = it)
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Column() {
                                        Text(
                                            text = "Metacritic Rating",
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                        state.gameDetails?.metacritic?.let {
                                            Text(
                                                text = it.toString(),
                                                fontSize = 30.sp,
                                                color = MaterialTheme.colorScheme.tertiary
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Column() {
                                    Text(
                                        text = "Game Platforms",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                    LazyRow() {
                                        state.gameDetails?.platforms?.let {
                                            items(it.size) { i ->
                                                state.gameDetails.platforms[i]?.let {
                                                    SinglePlatform(name = it)
                                                    Spacer(modifier = Modifier.width(5.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Column() {
                                    Text(
                                        text = "Game Publisher",
                                        color = MaterialTheme.colorScheme.tertiary,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        state.gameDetails?.publisher?.let {
                                            Text(
                                                text = it,
                                                fontSize = 20.sp,
                                                color = MaterialTheme.colorScheme.tertiary
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Column() {
                                    Text(
                                        text = "Tags",
                                        color = MaterialTheme.colorScheme.tertiary,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    LazyRow() {
                                        state.gameDetails?.genres?.let {
                                            items(it.size) { i ->
                                                state.gameDetails.genres[i]?.let { name ->
                                                    SingleGenre(name = name)
                                                    Spacer(modifier = Modifier.width(5.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Column() {
                                    Text(
                                        text = "Game Details",
                                        color = MaterialTheme.colorScheme.tertiary,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    state.gameDetails?.let {
                                        it.description?.let { it1 ->
                                            Text(
                                                text = it1,
                                                color = MaterialTheme.colorScheme.tertiary,
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                    Text(
                                        text = "PC minimum requirements",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                    state.gameDetails?.let {
                                        it.pcRequirements?.let { it1 ->
                                            Text(
                                                fontSize = 20.sp,
                                                text = it1,
                                                color = MaterialTheme.colorScheme.tertiary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
}
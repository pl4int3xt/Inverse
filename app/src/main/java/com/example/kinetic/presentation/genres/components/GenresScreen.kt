package com.example.kinetic.presentation.genres.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kinetic.R
import com.example.kinetic.presentation.genres.GenresScreenViewModel
import com.example.kinetic.presentation.uievent.UiEvent
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        Box(modifier = Modifier.fillMaxSize()){
            when(genres.loadState.refresh){
                is LoadState.Loading -> {
                    val lottieCompositionSpec by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
                        R.raw.gaming))
                    LottieAnimation(
                        composition = lottieCompositionSpec,
                        iterations = Int.MAX_VALUE,
                        alignment = Alignment.Center
                    )
                }
                is LoadState.Error -> {
                    IconButton(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = { genres.refresh() }) {
                        Icon(
                            tint = MaterialTheme.colorScheme.tertiary,
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "refresh icon"
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
                                        modifier = Modifier.height(50.dp)
                                    ) {

                                    }
                                }
                                item {
                                    Column(
                                        horizontalAlignment = Alignment.Start,
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxWidth()
                                        ,
                                    ) {
                                        Text(
                                            text = "Games Genres",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 35.sp,
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                }
                                items(genres) { genre ->
                                    if (genre != null) {
                                        SingleGenre(
                                            image = genre.imageBackground,
                                            title = genre.slug,
                                            gameCount = genre.gamesCount
                                        )
                                    }
                                }
                                if (genres.loadState.append is LoadState.Loading) {
                                    item {
                                        CircularProgressIndicator()
                                    }
                                }
                                if (genres.loadState.append is LoadState.Error){
                                    item {
                                        (genres.loadState.append as LoadState.Error).error.message?.let {
                                                it1 -> Text(
                                            modifier = Modifier.padding(16.dp),
                                            text = it1,
                                            color = Color.Red)
                                        }
                                        IconButton(onClick = { genres.refresh() }) {
                                            Icon(
                                                imageVector = Icons.Filled.Refresh,
                                                contentDescription = "Refresh"
                                            )
                                        }
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
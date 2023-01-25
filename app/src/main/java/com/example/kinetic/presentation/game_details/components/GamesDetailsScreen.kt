package com.example.kinetic.presentation.game_details.components

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.kinetic.R
import com.example.kinetic.presentation.game_details.GamesDetailsScreenEvents
import com.example.kinetic.presentation.game_details.GamesDetailsScreenViewModel
import com.example.kinetic.presentation.shared.MainTopAppBar
import com.example.kinetic.presentation.uievent.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun GameDetailsScreen(
    onPopBackStack: () -> Unit,
    viewModel: GamesDetailsScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
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
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading, onRefresh = {
            viewModel.getGameDetails()
        }
    )

    PullRefreshIndicator(
        refreshing = state.isLoading,
        state = pullRefreshState,
        contentColor = MaterialTheme.colorScheme.primary
    )

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "",
                navigationIcon = Icons.Default.ArrowBack,
                onClickNavigation = { viewModel.onEvent(GamesDetailsScreenEvents.OnCancelClicked) }) {
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pullRefresh(pullRefreshState)
                .fillMaxHeight(1f)
        ) {
            LazyColumn(){
                item {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.6f)
                        ) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                model = state.gameDetails?.backgroundImage, contentDescription = "image"
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(0.4f)
                        ) {
                            state.gameDetails?.let { it1 ->
                                Text(
                                    text = it1.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row {
                                state.gameDetails?.esrbRating?.let {
                                    when(it){
                                        "Mature" -> {
                                            EsrbRating(image = R.drawable.mature, description = "mature")
                                        }
                                        "Teen" -> {
                                            EsrbRating(image = R.drawable.teen, description = "mature")
                                        }
                                        "Everyone" -> {
                                            EsrbRating(image = R.drawable.everyone, description = "mature")
                                        }
                                        else -> {
                                            EsrbRating(name = it)
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Column() {
                                    Text(text = "Metacritic")
                                    state.gameDetails?.metacritic?.let {
                                        Text(
                                            text = it.toString(),
                                            fontSize = 30.sp
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Column() {
                                Text(
                                    text = "Game Platforms",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                LazyRow(
                                ){
                                    state.gameDetails?.platforms?.let {
                                        items(it.size){ i ->
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
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    state.gameDetails?.publisher?.let {
                                        Text(
                                            text = it,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Column() {
                                Text(
                                    text = "Tags",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                LazyRow(
                                    modifier = Modifier.padding(10.dp)
                                ){
                                    state.gameDetails?.genres?.let {
                                        items(it.size){ i ->
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
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                state.gameDetails?.let {
                                    Text(
                                        text = it.description,
                                        fontSize = 20.sp
                                    )
                                }
                                Text(
                                    text = "PC minimum requirements",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                state.gameDetails?.let {
                                    Text(
                                        fontSize = 20.sp,
                                        text = it.pcRequirements) }
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = state.isLoading,
                contentColor = MaterialTheme.colorScheme.primary,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
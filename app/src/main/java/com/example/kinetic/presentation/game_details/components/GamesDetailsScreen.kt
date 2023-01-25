package com.example.kinetic.presentation.game_details.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
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

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(),
        topBar = {
            MainTopAppBar(
                scrollBehavior = scrollBehavior,
                title = "",
                navigationIcon = Icons.Default.ArrowBack,
                onClickNavigation = { viewModel.onEvent(GamesDetailsScreenEvents.OnCancelClicked) }) {
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ) {
                state.gameDetails?.let { it1 ->
                    Text(
                        text = it1.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Row() {
                    state.gameDetails?.esrbRating?.let { Text(text = it) }
                    state.gameDetails?.metacritic?.let { Text(text = it.toString()) }
                }
                LazyRow(){
                    state.gameDetails?.platforms?.let {
                        items(it.size){ i ->
                            state.gameDetails.platforms[i]?.let { Text(text = it) }
                        }
                    }
                }
                Row() {
                    state.gameDetails?.publisher?.let { Text(text = it) }
                    AsyncImage(model = state.gameDetails?.publisherImage, contentDescription = "publisher image")
                }
                LazyRow(){
                    state.gameDetails?.genres?.let {
                        items(it.size){ i ->
                            state.gameDetails.genres[i]?.let { Text(text = it) }
                        }
                    }
                }
                state.gameDetails?.let { Text(text = it.description) }
                state.gameDetails?.let { Text(text = it.pcRequirements) }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pullRefresh(pullRefreshState)
                .fillMaxHeight(1f)
        ) {
            LazyColumn(){
                item {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f),
                        contentAlignment = Alignment.Center
                    ){
                        AsyncImage(
                            contentScale = ContentScale.FillBounds,
                            model = state.gameDetails?.backgroundImage, contentDescription = "image")
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
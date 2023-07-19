package com.example.kinetic.presentation.games_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinetic.constants.Resource
import com.example.kinetic.domain.model.GameModel
import com.example.kinetic.presentation.search.PAGE_SIZE
import com.example.kinetic.presentation.search.SearchScreenEvents
import com.example.kinetic.presentation.search.SearchScreenState
import com.example.kinetic.presentation.uievent.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GamesListViewModel @Inject constructor(

): ViewModel() {
    val currentGames: MutableState<List<GameModel>> = mutableStateOf(ArrayList())

    val page = mutableStateOf(1)
    private var gamesScrollPosition = 0

    var searchQuery by mutableStateOf("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

//    fun searchGame(){
//        resetSearchState()
//        searchGameUseCase(searchQuery, page.value, PAGE_SIZE).onEach { result ->
//            when(result){
//                is Resource.Loading -> {
//                    _state.value = SearchScreenState(isLoading = true)
//                }
//                is Resource.Error -> {
//                    _state.value = SearchScreenState(message = result.message?: "Unexpected error occurred")
//                    sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
//                }
//                is Resource.Success -> {
//                    this.currentGames.value = result.data?: emptyList()
//                    _state.value = SearchScreenState(isLoading = false)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

//    fun nextPage(){
//        if((gamesScrollPosition + 1) >= (page.value * PAGE_SIZE)){
//            _state.value = SearchScreenState(isNextLoading = true)
//            incrementPage()
//            if (page.value > 1){
//                searchGameUseCase(searchQuery,page.value, PAGE_SIZE).onEach { result ->
//                    when(result){
//                        is Resource.Error -> {
//                            _state.value = SearchScreenState(message = result.message?: "Unexpected error occurred")
//                            sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
//                        }
//                        is Resource.Success -> {
//                            appendGames(result.data?: emptyList())
//                        } else -> Unit
//                    }
//                }.launchIn(viewModelScope)
//            }
//            _state.value = SearchScreenState(isNextLoading = false)
//        }
//    }
    private fun appendGames(games: List<GameModel>){
        val current = ArrayList(this.currentGames.value)
        current.addAll(games)
        this.currentGames.value = current
    }

    fun resetSearchState(){
        page.value = 1
        onChangeGamesScrollPosition(0)
        this.currentGames.value = listOf()
    }
    private fun incrementPage(){
        page.value = page.value + 1
    }

    fun onChangeGamesScrollPosition(position: Int){
        gamesScrollPosition = position
    }

    fun onEvent(gamesListEvents: GamesListEvents){
        when(gamesListEvents){
            is GamesListEvents.OnGameClicked -> {

            }
            is GamesListEvents.OnPopBackStack -> {

            }
        }
    }
}
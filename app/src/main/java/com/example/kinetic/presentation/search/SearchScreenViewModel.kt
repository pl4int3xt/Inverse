package com.example.kinetic.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.kinetic.data.remote.dto.GameDto
import com.example.kinetic.data.remote.dto.toGameModel
import com.example.kinetic.domain.model.GameModel
import com.example.kinetic.domain.repository.GameRepository
import com.example.kinetic.domain.use_case.SearchGameUseCase
import com.example.kinetic.presentation.uievent.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {
    var searchQuery by mutableStateOf("")

    fun getGames(): Flow<PagingData<GameModel>>{
        val gamesPager = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchGameUseCase(repository, searchQuery)
            }
        )
            .flow
            .cachedIn(viewModelScope)
            .map {
                pagingData ->
                pagingData.map {
                    it.toGameModel()
                }
            }
        return gamesPager
    }

//    val currentGames: MutableState<List<GameModel>> = mutableStateOf(ArrayList())

//    val page = mutableStateOf(1)
//    private var gamesScrollPosition = 0
//
//    var searchQuery by mutableStateOf("")
//
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
//
//    private val _state = mutableStateOf(SearchScreenState())
//    val state: State<SearchScreenState> = _state
//
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
//
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
//    private fun appendGames(games: List<GameModel>){
//        val current = ArrayList(this.currentGames.value)
//        current.addAll(games)
//        this.currentGames.value = current
//    }
//
//    fun resetSearchState(){
//        page.value = 1
//        onChangeGamesScrollPosition(0)
//        this.currentGames.value = listOf()
//    }
//    private fun incrementPage(){
//        page.value = page.value + 1
//    }
//
//    fun onChangeGamesScrollPosition(position: Int){
//        gamesScrollPosition = position
//    }
//
    fun onEvent(searchScreenEvents: SearchScreenEvents){
        when(searchScreenEvents){
            is SearchScreenEvents.OnSearchClicked -> {
                getGames()
            }
            is SearchScreenEvents.OnBackClicked -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is SearchScreenEvents.OnSearchQueryChanged ->{
                searchQuery = searchScreenEvents.search
            }
        }
    }
    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}
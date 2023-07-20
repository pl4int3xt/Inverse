package com.example.kinetic.presentation.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.kinetic.data.remote.dto.GenreDto
import com.example.kinetic.data.remote.dto.toGameModel
import com.example.kinetic.data.remote.dto.toGenreModel
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.uievent.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresScreenViewModel @Inject constructor(
    pager: Pager<Int, GenreDto>
): ViewModel(){

    val pagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toGenreModel() }
        }
        .cachedIn(viewModelScope)

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    var uiEvents = _uiEvents.asSharedFlow()
    fun onEvent(genresScreenEvents: GenresScreenEvents){
        when(genresScreenEvents){
            is GenresScreenEvents.OnGenreClicked -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvent.OnNavigate(Screens.GenreScreen.route + "/${genresScreenEvents.genre}"))
                }
            }
        }
    }
}
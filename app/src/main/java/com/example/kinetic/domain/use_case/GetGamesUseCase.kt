package com.example.kinetic.domain.use_case

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.example.kinetic.constants.Resource
import com.example.kinetic.data.remote.dto.GameDto
import com.example.kinetic.data.remote.dto.GamesDto
import com.example.kinetic.data.remote.dto.toGameModel
import com.example.kinetic.domain.model.GameModel
import com.example.kinetic.domain.repository.GameRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
): PagingSource<Int, GameDto>() {
    override fun getRefreshKey(state: PagingState<Int, GameDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDto> {
        return try {
            val currentPage = params.key ?: 1
            val response = gameRepository.getGames(currentPage, pageSize = 20)
            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}
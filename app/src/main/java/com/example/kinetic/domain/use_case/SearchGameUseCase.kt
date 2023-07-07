package com.example.kinetic.domain.use_case

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinetic.data.remote.dto.GameDto
import com.example.kinetic.domain.repository.GameRepository
import javax.inject.Inject

class SearchGameUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val searchQuery: String
) : PagingSource<Int, GameDto>(){
    override fun getRefreshKey(state: PagingState<Int, GameDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDto> {
        return try {
            val currentPage = params.key ?: 1
            val response = gameRepository.searchGame(
                searchQuery = searchQuery,
                pageSize = 20,
                page = currentPage
            )
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
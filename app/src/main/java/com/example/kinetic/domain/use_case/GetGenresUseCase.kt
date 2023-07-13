package com.example.kinetic.domain.use_case

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinetic.data.remote.dto.GenreDto
import com.example.kinetic.domain.repository.GameRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: GameRepository
) : PagingSource<Int, GenreDto>() {
    override fun getRefreshKey(state: PagingState<Int, GenreDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GenreDto> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getGenres(currentPage, pageSize = 20)
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
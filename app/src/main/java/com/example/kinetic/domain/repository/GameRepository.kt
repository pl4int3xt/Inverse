package com.example.kinetic.domain.repository

import com.example.kinetic.data.remote.dto.GamesDetailsDto
import com.example.kinetic.data.remote.dto.GamesDto
import com.example.kinetic.data.remote.dto.GenresResponse

interface GameRepository {
    suspend fun getGames(page: Int, pageSize: Int): GamesDto
    suspend fun getGamesDetails(gameId: String): GamesDetailsDto
    suspend fun searchGame(searchQuery: String, page: Int, pageSize: Int): GamesDto
    suspend fun getGenres(page:Int, pageSize: Int): GenresResponse
    suspend fun getGamesByCategory(page: Int, pageSize: Int, genre: String): GamesDto
}
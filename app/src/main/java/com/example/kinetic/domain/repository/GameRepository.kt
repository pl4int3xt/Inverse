package com.example.kinetic.domain.repository

import com.example.kinetic.data.remote.dto.GamesDetailsDto
import com.example.kinetic.data.remote.dto.GamesDto

interface GameRepository {
    suspend fun getGames(page: Int): GamesDto
    suspend fun getGamesDetails(gameId: String): GamesDetailsDto
    suspend fun searchGame(searchQuery: String, page: Int): GamesDto
}
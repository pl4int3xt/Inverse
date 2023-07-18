package com.example.kinetic.data.remote.repository

import com.example.kinetic.constants.Constants
import com.example.kinetic.data.remote.dto.GamesDetailsDto
import com.example.kinetic.data.remote.dto.GamesDto
import com.example.kinetic.data.remote.dto.GenresResponse
import com.example.kinetic.domain.repository.GameRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): GameRepository {
    override suspend fun getGames(page: Int, pageSize: Int): GamesDto {
        return httpClient.get {
            url(Constants.GET_GAMES_URL)
            parameter("page", page)
            parameter("page_size", pageSize)
            parameter("key", Constants.API_KEY)
        }
    }

    override suspend fun getGamesDetails(gameId: String): GamesDetailsDto {
        return httpClient.get(path = gameId) {
            url("https://api.rawg.io/api/games/$gameId")
            parameter("key", Constants.API_KEY)
        }
    }

    override suspend fun searchGame(searchQuery: String, page: Int, pageSize: Int): GamesDto {
        return httpClient.get {
            url(Constants.GET_GAMES_URL)
            parameter("page", page)
            parameter("page_size", pageSize)
            parameter("search", searchQuery)
            parameter("key", Constants.API_KEY)
        }
    }

    override suspend fun getGenres(page: Int, pageSize: Int): GenresResponse {
        return httpClient.get {
            url(Constants.GET_GENRES_URL)
            parameter("page", page)
            parameter("page_size", pageSize)
            parameter("key", Constants.API_KEY)
        }
    }

    override suspend fun getGamesByCategory(page: Int, pageSize: Int, genre: String): GamesDto {
        return httpClient.get {
            url(Constants.GET_GAMES_URL)
            parameter("page", page)
            parameter("genres", genre)
            parameter("page_size", pageSize)
            parameter("key", Constants.API_KEY)
        }
    }
}
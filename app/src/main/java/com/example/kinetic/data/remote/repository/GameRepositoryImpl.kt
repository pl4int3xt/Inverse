package com.example.kinetic.data.remote.repository

import com.example.kinetic.constants.Constants
import com.example.kinetic.data.remote.dto.GamesDetailsDto
import com.example.kinetic.data.remote.dto.GamesDto
import com.example.kinetic.domain.repository.GameRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.parseQueryStringTo
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
): GameRepository {
    override suspend fun getGames(page: Int): GamesDto {
        return httpClient.get {
            url(Constants.GET_GAMES_URL)
            parameter("page", page)
            parameter("key", "ddcd58c5aaef4a71981eff6c99e548f4")
        }
    }

    override suspend fun getGamesDetails(gameId: String): GamesDetailsDto {
        return httpClient.get(path = gameId) {
            url(Constants.GET_GAME_DETAILS)
            parameter("key", "ddcd58c5aaef4a71981eff6c99e548f4")
        }
    }
}
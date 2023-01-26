package com.example.kinetic.domain.use_case

import com.example.kinetic.constants.Resource
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

class SearchGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(searchQuery: String ,page: Int): Flow<Resource<List<GameModel>>> = flow {
        try {
            emit(Resource.Loading())
            val games = gameRepository.searchGame(searchQuery, page).results.map { it.toGameModel() }
            emit(Resource.Success(games))
        } catch (e: RedirectResponseException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: ClientRequestException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: ServerResponseException){
            emit(Resource.Error("Error: ${e.response.status.description}"))
        } catch (e: IOException){
            emit(Resource.Error("Can't reach server, check your internet connection"))
        }
    }
}
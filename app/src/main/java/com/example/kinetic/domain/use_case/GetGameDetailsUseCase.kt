package com.example.kinetic.domain.use_case

import com.example.kinetic.constants.Resource
import com.example.kinetic.data.remote.dto.toGameDetailsModel
import com.example.kinetic.domain.model.GameDetailsModel
import com.example.kinetic.domain.repository.GameRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(gameId: String): Flow<Resource<GameDetailsModel>> = flow {
        try {
            emit(Resource.Loading())
            val details = gameRepository.getGamesDetails(gameId).toGameDetailsModel()
            emit(Resource.Success(details))
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
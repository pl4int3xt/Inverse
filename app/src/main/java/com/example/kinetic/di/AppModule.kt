package com.example.kinetic.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.kinetic.data.remote.dto.GameDto
import com.example.kinetic.data.remote.dto.GenreDto
import com.example.kinetic.data.remote.repository.GameRepositoryImpl
import com.example.kinetic.domain.repository.GameRepository
import com.example.kinetic.domain.use_case.GetGamesUseCase
import com.example.kinetic.domain.use_case.GetGenresUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideGameRepository(): GameRepository {
        return GameRepositoryImpl(httpClient = HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = GsonSerializer() {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                }
            }
        })
    }

    @Singleton
    @Provides
    fun provideGamesPager(gameRepository: GameRepository): Pager<Int, GameDto> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                GetGamesUseCase(gameRepository)
            }
        )
    }

    @Singleton
    @Provides
    fun provideGenrePager(gameRepository: GameRepository): Pager<Int, GenreDto> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                GetGenresUseCase(gameRepository)
            }
        )
    }
//    @Singleton
//    @Provides
//    fun provideSearchGamesPager(gameRepository: GameRepository, searchQuery: String): Pager<Int, GameDto> {
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            pagingSourceFactory = {
//                SearchGameUseCase(gameRepository, searchQuery)
//            }
//        )
//    }
}
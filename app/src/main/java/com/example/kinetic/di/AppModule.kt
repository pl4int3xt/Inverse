package com.example.kinetic.di

import com.example.kinetic.data.remote.repository.GameRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    fun provideGameRepository(): GameRepositoryImpl {
        return GameRepositoryImpl(httpClient = HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        })
    }
}
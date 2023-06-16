package com.example.getmoview.di

import android.app.Application
import androidx.room.Room
import com.example.getmoview.common.Constants.DATABASE_NAME
import com.example.getmoview.data.local.MovieDatabase
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.data.remote.MovieApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieAppModule {

    // Injects database
    @Singleton
    @Provides
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()

        //allowMainThreadQueries(). This allows room to make database queries in the main thread since room doesn't allow this by default.
        //fallbackToDestructiveMigration(). Allows room to destructively recreate database tables if migration is not found.
    }

    // Injecting the ktor client
    @Singleton
    @Provides
    fun provideMovieDatabase(): MovieApi{
        return MovieApiImpl(
            client = HttpClient(Android){
                install(Logging){
                    level = LogLevel.ALL
                }
                install(JsonFeature){
                    serializer = KotlinxSerializer()
                }
            }
        )
    }
}
package com.example.getmoview.di

import android.app.Application
import androidx.room.Room
import com.example.getmoview.common.Constants.BASE_URL
import com.example.getmoview.common.Constants.DATABASE_NAME
import com.example.getmoview.data.local.MovieDatabase
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.data.remote.TokenInterceptor
import com.example.getmoview.data.repositoryImpl.MovieRepositoryImpl
import com.example.getmoview.domain.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieAppModule {
    @Provides
    @Singleton
    fun provideMovieApi(gson: Gson): MovieApi {
        return Retrofit.Builder().client(provideInterceptor()).addConverterFactory(
            GsonConverterFactory.create(gson)
        ).baseUrl(BASE_URL).build().create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(TokenInterceptor()).addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    // Injects database
    @Singleton
    @Provides
    fun provideMovieDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application, MovieDatabase::class.java, DATABASE_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRepository(api: MovieApi, db: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(api, db.moviesDao, db.showsDao, db.genreDao)
    }
}
package com.example.getmoview.use_case

import com.example.getmoview.common.Constants.API_KEY
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.MovieType
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.MovieRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val api: MovieApi
    ){

    suspend operator fun invoke(): Flow<Resources<List<MovieEntity>>> = flow {
        try {
           val apiData = api.getPopularMovies()
            repository.saveMovie(apiData, MovieType.POPULAR)

            emit(
                Resources.Success(data = repository.getLocalPopularMovies())
            )

        }catch (e: RedirectResponseException){
            emit(
                Resources.Error(
                    data = repository.getLocalPopularMovies(),
                    message = "An error occurred"
                )
            )

        }catch (e: ClientRequestException){
            emit(
                Resources.Error(
                    data = repository.getLocalPopularMovies(),
                    message = "You have no internet connection"
                )
            )
        }
    }
}
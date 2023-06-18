package com.example.getmoview.use_case

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.MovieType
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.MovieRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val api: MovieApi
) {

    suspend operator fun invoke(): Flow<Resources<List<MovieEntity>>> = flow {
        try {
            val api = api.getTopRatedMovies()
            repository.saveMovie(api, movieType = MovieType.TOP_RATED)

            emit(
                Resources.Success(data = repository.getLocalTopRated())
            )
        } catch (e: RedirectResponseException) {

            emit(
                Resources.Error(
                    data = repository.getLocalTopRated(),
                    message = "An error occurred"
                )
            )
        } catch (e: ClientRequestException) {
            emit(
                Resources.Error(
                    data = repository.getLocalTopRated(),
                    message = "You have no internet connection"
                )
            )
        }
    }
}
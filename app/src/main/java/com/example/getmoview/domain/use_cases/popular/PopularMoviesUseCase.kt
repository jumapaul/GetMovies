package com.example.getmoview.domain.use_cases.popular

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    operator fun invoke(): Flow<Resources<List<MoviesEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.savePopularMovies(1)

            emit(Resources.Success(data = repository.getLocalPopularMovies()))
        } catch (e: RedirectResponseException) {
            emit(
                Resources.Error(
                    data = repository.getLocalPopularMovies(),
                    message = e.localizedMessage ?: "An error occurred"
                )
            )

        } catch (e: ClientRequestException) {
            emit(
                Resources.Error(
                    data = repository.getLocalPopularMovies(),
                    message = "No internet connection"
                )
            )
        }
    }
}
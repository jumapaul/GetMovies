package com.example.getmoview.use_case

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.entity.TrendingMoviesEntity
import com.example.getmoview.domain.MovieRepository
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): Flow<Resources<List<TrendingMoviesEntity>>> = flow {
        try {
            repository.saveTrendingMovies()

            emit(
                Resources.Error(
                    data = repository.getLocalTrendingMovies(),
                    message = "An error occurred"
                )
            )
        }catch (e: RedirectResponseException){
            emit(
                Resources.Error(
                    data = repository.getLocalTrendingMovies(),
                    message = "No internet connection"
                )
            )

        }
    }
}
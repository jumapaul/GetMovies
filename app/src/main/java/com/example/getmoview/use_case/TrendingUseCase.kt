package com.example.getmoview.use_case

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.entity.TrendingMoviesEntity
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.MovieRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.math.log

class TrendingUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val api: MovieApi
) {

    suspend operator fun invoke(): Flow<Resources<List<TrendingMoviesEntity>>> = flow {
        try {
            val data = api.getTrendingMovies()

            repository.saveTrendingMovies(data)

            emit(
                Resources.Success(
                    data = repository.getLocalTrendingMovies()
                )
            )
        } catch (e: RedirectResponseException) {
            emit(
                Resources.Error(
                    data = repository.getLocalTrendingMovies(),
                    message = "An error occurred"
                )
            )
        } catch (e: ClientRequestException) {
            emit(
                Resources.Error(
                    data = repository.getLocalTrendingMovies(),
                    message = "No internet connection"
                )
            )
        }
    }
}
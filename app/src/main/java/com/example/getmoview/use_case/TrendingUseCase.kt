package com.example.getmoview.use_case

import com.example.getmoview.common.Resources
import com.example.getmoview.domain.repository.MovieRepository
import com.example.getmoview.domain.model.trending.Results
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    operator fun invoke(): Flow<Resources<List<Results>>> = flow {
        try {
            emit(Resources.IsLoading())
            val data = repository.getTrendingMovies().results
            emit(Resources.Success(data))
        } catch (e: RedirectResponseException) {
            emit(
                Resources.Error(e.localizedMessage?: "An error occurred")
            )
        } catch (e: ClientRequestException) {
            emit(
                Resources.Error(message = "No internet connection")
            )
        }
    }
}
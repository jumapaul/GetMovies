package com.example.getmoview.domain.use_cases.shows

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resources<List<ShowsEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.saveShows(1)

            emit(Resources.Success(data = repository.getShows()))
        } catch (e: RedirectResponseException) {
            Resources.Error(
                data = repository.getShows(),
                message = e.localizedMessage ?: "An error occurred"
            )
        } catch (e: ClientRequestException) {
            Resources.Error(
                data = repository.getShows(),
                message = "No internet connection"
            )
        }
    }
}
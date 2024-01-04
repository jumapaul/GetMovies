package com.example.getmoview.domain.use_cases.shows

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(showsEntity: ShowsEntity): Flow<Resources<List<ShowsEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.updateShowsList(showsEntity)
            emit(Resources.Success(data = repository.getLocalShows()))

        } catch (e: RedirectResponseException) {
            emit(
                Resources.Error(
                    message = e.localizedMessage ?: "An error occurred"
                )
            )
        } catch (e: ClientRequestException) {
            emit(
                Resources.Error(
                    message = "No internet connection"
                )
            )
        }
    }
}
package com.example.getmoview.domain.use_cases.local.local_shows_use_case

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resources<List<ShowsEntity>>> = flow {
        try {
            emit(Resources.Success(data = repository.getLocalShows()))
        }catch (e: RedirectResponseException){
            emit(Resources.Error(data = null, message = "An error occurred"))
        }
    }
}
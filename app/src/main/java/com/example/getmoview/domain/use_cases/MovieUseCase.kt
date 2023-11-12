package com.example.getmoview.domain.use_cases

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(page: Int): Flow<Resources<List<MoviesEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.saveMovieItems(page)
            emit(Resources.Success(data = repository.getLocalMovies()))
        } catch (e: RedirectResponseException) {
            emit(Resources.Error(
                data = repository.getLocalMovies(),
                message = e.localizedMessage ?: "An error occurred"))
        } catch (e: ClientRequestException) {
            emit(Resources.Error(
                data = repository.getLocalMovies(),
                message = "You have no internet connection"))
        }
    }
}
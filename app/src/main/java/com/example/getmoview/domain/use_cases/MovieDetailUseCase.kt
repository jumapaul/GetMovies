package com.example.getmoview.domain.use_cases

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.client.features.RedirectResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(movieId: Int): Flow<Resources<MoviesEntity>> = flow {
        try {
            emit(Resources.IsLoading())
            val movie = repository.getLocalMovieById(movieId)

            emit(Resources.Success(movie))
        } catch (e: RedirectResponseException) {
            emit(Resources.Error(message = "An expected error occurred"))
        } catch (e: IOException){
            emit(Resources.Error(message = "No internet connection"))
        }
    }
}
package com.example.getmoview.domain.use_cases

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.math.log

class UpdateMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(movie: MoviesEntity): Flow<Resources<List<MoviesEntity>>> = flow {

        try {
            emit(Resources.IsLoading())
            repository.updateMoviesList(movie)
            emit(Resources.Success(data = repository.getLocalMovies()))
        }catch (e: RedirectResponseException){
            emit(
                Resources.Error(
                    message = e.localizedMessage ?: "An error occurred"
                )
            )
        }catch (e: ClientRequestException){
            emit(
                Resources.Error(
                    message = "No internet connection"
                )
            )
        }
    }
}
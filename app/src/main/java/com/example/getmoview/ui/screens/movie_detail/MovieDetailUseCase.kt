package com.example.getmoview.ui.screens.movie_detail

import com.example.getmoview.common.Resources
import com.example.getmoview.domain.repository.MovieRepository
import com.example.getmoview.domain.model.MovieDtoItem
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(movieId: Int): Flow<Resources<MovieDtoItem>> = flow {
        try {
            emit(Resources.IsLoading())
            val movie = repository.getMovieById(movieId)

            emit(Resources.Success(movie))
        } catch (e: HttpException) {
            emit(Resources.Error(data = null, message = "An expected error occurred"))
        } catch (e: IOException){
            emit(Resources.Error(data = null, message = "No internet connection"))
        }
    }
}
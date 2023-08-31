package com.example.getmoview.ui.screens.movies_category.popular

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.repository.MovieRepository
import com.example.getmoview.domain.model.MovieDtoItem
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    operator fun invoke(): Flow<Resources<List<MovieDtoItem>>> = flow {
        try {
            emit(Resources.IsLoading())
            val movies = repository.getPopularMovies().results
            emit(Resources.Success(data = movies))
        } catch (e: HttpException) {
            emit(Resources.Error(e.localizedMessage?: "An error occurred"))

        } catch (e: IOException) {
            emit(
                Resources.Error(
                    message = "No internet connection"
                )
            )
        }
    }
}
package com.example.getmoview.ui.screens.movie

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.repository.MovieRepository
import com.example.getmoview.domain.model.MovieDtoItem
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class MovieUseCase @Inject constructor(
     private val repository: MovieRepository
) {

    operator fun invoke(page: Int): Flow<Resources<List<MovieDtoItem>>> = flow {
        try {
            val api = repository.getMovies(page)
            emit(Resources.Success(data = api.results))
        } catch (e: HttpException) {
            emit(Resources.Error(message = "An error occurred"))
        } catch (e: IOException) {
            emit(Resources.Error(message = "You have no internet connection"))
        }
    }
}
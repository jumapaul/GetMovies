package com.example.getmoview.use_case

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.model.genre.Genre
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resources<List<Genre>>> = flow {
        try {
            emit(Resources.IsLoading())
            val genre = repository.getGenres()
            emit(Resources.Success(data = genre.genres))
        } catch (e: HttpException) {
            emit(Resources.Error(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(
                Resources.Error(
                    message = "No internet connection"
                )
            )
        }
    }
}
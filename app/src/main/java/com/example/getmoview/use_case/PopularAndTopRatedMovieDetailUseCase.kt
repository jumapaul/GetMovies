package com.example.getmoview.use_case

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularAndTopRatedMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(movieId: Int): Flow<Resources<MovieEntity>> = flow {
        try {
            emit(Resources.IsLoading())
            val movie = repository.getPopularAndTopRatedMovieById(movieId)

            Log.d("------->", "invoke: $movie")
            emit(Resources.Success(movie))
        } catch (e: Exception) {
            emit(Resources.Error(data = null, message = "An expected error occurred"))
        }
    }
}
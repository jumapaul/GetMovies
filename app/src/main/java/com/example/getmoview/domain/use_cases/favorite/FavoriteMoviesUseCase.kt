package com.example.getmoview.domain.use_cases.favorite

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class FavoriteMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(isFavorite: Boolean): Flow<Resources<List<MoviesEntity>>> = flow {
        try {
            emit(Resources.IsLoading())


            emit(Resources.Success(data = repository.getFavoriteMovies(isFavorite)))

        }catch (e: IOException){
            emit(
                Resources.Error(
                    message = e.localizedMessage ?: "An error occurred"
                )
            )
        }
    }
}
package com.example.getmoview.domain.use_cases.local.local_movie_use_case

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resources<List<MoviesEntity>>> = flow {
        try {
            emit(Resources.Success(data = repository.getLocalMovies()))
        }catch (e:RedirectResponseException){
            emit(Resources.Error(data = null, message = "An error occurred"))
        }
    }
//    fun getAllFavoriteMovies(): List<MoviesEntity> = repository.getLocalMovies()
//
//    suspend fun addFavoriteMovie(moviesEntity: MoviesEntity) = repository.insertMovies(moviesEntity)
//
//    suspend fun getFavoriteMovieById(id: Int) = repository.getMovieById(id)
//
//    suspend fun deleteFavorite(moviesEntity: MoviesEntity) = repository.deleteMovie(moviesEntity)
}
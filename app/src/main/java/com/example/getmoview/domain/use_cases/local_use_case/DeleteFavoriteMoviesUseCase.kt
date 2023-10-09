package com.example.getmoview.domain.use_cases.local_use_case

import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository

class DeleteFavoriteMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(moviesEntity: MoviesEntity){
        repository.deleteMovie(moviesEntity)
    }
}
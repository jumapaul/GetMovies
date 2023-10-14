package com.example.getmoview.domain.use_cases.local.local_movie_use_case

import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import javax.inject.Inject

class AddFavoriteMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(moviesEntity: MoviesEntity){
        repository.insertMovies(moviesEntity)
    }
}
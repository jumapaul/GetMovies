package com.example.getmoview.domain.use_cases.local_use_case

import com.example.getmoview.domain.repository.MovieRepository

class GetFavoriteMovieByIdUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int){
        repository.getMovieById(id)
    }
}
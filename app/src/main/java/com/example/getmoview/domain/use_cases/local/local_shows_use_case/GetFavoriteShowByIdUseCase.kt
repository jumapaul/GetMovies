package com.example.getmoview.domain.use_cases.local.local_shows_use_case

import com.example.getmoview.domain.repository.MovieRepository
import javax.inject.Inject

class GetFavoriteShowByIdUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int){
        repository.getLocalShowsById(id)
    }
}
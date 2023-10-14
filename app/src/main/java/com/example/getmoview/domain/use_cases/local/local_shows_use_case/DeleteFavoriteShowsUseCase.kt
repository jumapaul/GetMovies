package com.example.getmoview.domain.use_cases.local.local_shows_use_case

import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.repository.MovieRepository
import javax.inject.Inject

class DeleteFavoriteShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(showsEntity: ShowsEntity) {
        repository.deleteShows(showsEntity)
    }
}
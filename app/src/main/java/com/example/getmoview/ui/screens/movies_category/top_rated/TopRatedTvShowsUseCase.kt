package com.example.getmoview.ui.screens.movies_category.top_rated

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.model.top_rated_shows.TopRatedShowItem
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopRatedTvShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(page: Int): Flow<Resources<List<TopRatedShowItem>>> = flow {
        try {
            emit(Resources.IsLoading())
            val apiData = repository.getTopRatedTvShows(page).results
            emit(Resources.Success(data = apiData))

        } catch (e: HttpException) {
            emit(
                Resources.Error(e.localizedMessage ?: "An error occurred")
            )

        } catch (e: IOException) {
            emit(Resources.Error(message = "No internet connection"))
        }
    }
}
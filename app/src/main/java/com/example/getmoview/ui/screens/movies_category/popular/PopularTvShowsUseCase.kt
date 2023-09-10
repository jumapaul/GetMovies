package com.example.getmoview.ui.screens.movies_category.popular

import com.example.getmoview.common.Resources
import com.example.getmoview.domain.model.top_rated_shows.TvShowItem
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularTvShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(): Flow<Resources<List<TvShowItem>>> = flow {
        try {
            emit(Resources.IsLoading())
            val movies = repository.getPopularTvShows().results
            emit(Resources.Success(data = movies))
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
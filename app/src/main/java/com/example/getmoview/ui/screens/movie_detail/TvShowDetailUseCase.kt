package com.example.getmoview.ui.screens.movie_detail

import android.util.Log
import com.example.getmoview.common.Resources
import com.example.getmoview.domain.model.top_rated_shows.TvShowItem
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TvShowDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(showId: Int): Flow<Resources<TvShowItem>> = flow {
        try {
            val data = repository.getTvShowsById(showId)
            Log.d(">>>>>>>>", "setting id: $data")
            emit(Resources.Success(data = data))

        }catch (e: HttpException){
            emit(Resources.Error(message = "An error occurred"))

        }catch (e: IOException){
            emit(Resources.Error(message = "You have no internet connection"))
        }
    }
}
package com.example.getmoview.domain.use_cases

import com.example.getmoview.common.Resources
import com.example.getmoview.domain.model.top_shows.TvShowItem
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(page: Int): Flow<Resources<List<TvShowItem>>> = flow {
        try {
            val api = repository.getTvShows(page)
            emit(Resources.Success(data = api.results))
        }catch (e: HttpException){
            emit(Resources.Error(message = "An error occurred"))
        }catch (e: IOException){
            emit(Resources.Error(message = "No internet connection"))
        }
    }

}
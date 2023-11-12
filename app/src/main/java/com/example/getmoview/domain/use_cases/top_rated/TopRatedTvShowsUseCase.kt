package com.example.getmoview.domain.use_cases.top_rated

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopRatedTvShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(): Flow<Resources<List<ShowsEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.saveTopRatedShows(1)
            emit(Resources.Success(data = repository.getLocalTopRatedShows()))
        } catch (e: HttpException) {
            emit(
                Resources.Error(
                    data = repository.getLocalTopRatedShows(),
                    message = e.localizedMessage ?: "An error occurred")
            )

        } catch (e: IOException) {
            emit(Resources.Error(
                data = repository.getLocalTopRatedShows(),
                message = "No internet connection"))
        }
    }
}
package com.example.getmoview.domain.use_cases.popular

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularTvShowsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(): Flow<Resources<List<ShowsEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.savePopularShows(1)
            emit(Resources.Success(data = repository.getLocalPopularShows()))
        } catch (e: HttpException) {
            emit(
                Resources.Error(
                    data = repository.getLocalPopularShows(),
                    message = e.localizedMessage ?: "An error occurred"
                )
            )

        } catch (e: IOException) {
            emit(
                Resources.Error(
                    data = repository.getLocalPopularShows(),
                    message = "No internet connection"
                )
            )
        }
    }
}
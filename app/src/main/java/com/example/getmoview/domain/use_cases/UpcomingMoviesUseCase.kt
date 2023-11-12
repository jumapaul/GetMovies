package com.example.getmoview.domain.use_cases

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpcomingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resources<List<MoviesEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.saveUpcomingMovies(1)
            emit(Resources.Success(data = repository.getLocalUpcomingMovies()))
        } catch (e: HttpException) {
            emit(
                Resources.Error(
                    data = repository.getLocalUpcomingMovies(),
                    message = "An error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Resources.Error(
                    data = repository.getLocalUpcomingMovies(),
                    message = "You have no internet connection"
                )
            )
        }
    }
}
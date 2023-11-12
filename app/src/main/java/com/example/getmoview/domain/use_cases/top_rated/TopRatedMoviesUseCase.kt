package com.example.getmoview.domain.use_cases.top_rated

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.domain.repository.MovieRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class TopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(): Flow<Resources<List<MoviesEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.saveTopRatedMovies(1)
            emit(Resources.Success(data = repository.getLocalTopRatedMovies()))
        } catch (e: HttpException) {
            emit(
                Resources.Error(
                    data = repository.getLocalTopRatedMovies(),
                    message = e.localizedMessage?: "An error occurred")
            )
        } catch (e: IOException) {
            emit(
                Resources.Error(
                    data = repository.getLocalTopRatedMovies(),
                    message = "No internet connection"
                )
            )
        }
    }
}
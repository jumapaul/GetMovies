package com.example.getmoview.domain.use_cases

import com.example.getmoview.common.Resources
import com.example.getmoview.data.local.GenresIdsEntity
import com.example.getmoview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resources<List<GenresIdsEntity>>> = flow {
        try {
            emit(Resources.IsLoading())
            repository.saveGenres()

            emit(Resources.Success(data = repository.getLocalGenres()))
        } catch (e: HttpException) {
            emit(
                Resources.Error(
                    data = repository.getLocalGenres(),
                    message = e.localizedMessage ?: "An error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Resources.Error(
                    data = repository.getLocalGenres(),
                    message = "No internet connection"
                )
            )
        }
    }
}
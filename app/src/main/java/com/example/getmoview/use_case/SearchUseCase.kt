package com.example.getmoview.use_case

import com.example.getmoview.common.Resources
import com.example.getmoview.domain.MovieRepository
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.popular_top_rated.SearchedItem
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(
        searchQuery: String
    ): Flow<Resources<List<SearchedItem>>> = flow {
        try {
            val apiData = repository.search(searchQuery)

            emit(
                Resources.Success(data = apiData.results)
            )
        } catch (e: RedirectResponseException) {
            emit(Resources.Error(data = null, message = "An error occurred"))
        } catch (e: ClientRequestException) {
            emit(Resources.Error(data = null, message = "You have no internet connection"))
        }
    }
}
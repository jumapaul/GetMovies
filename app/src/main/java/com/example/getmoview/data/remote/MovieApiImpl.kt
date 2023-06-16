package com.example.getmoview.data.remote

import com.example.getmoview.common.Constants.POPULAR
import com.example.getmoview.common.Constants.TOP_RATED
import com.example.getmoview.common.Constants.TRENDING
import com.example.getmoview.domain.model.MovieDtoItem
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class MovieApiImpl @Inject constructor(private val client: HttpClient) : MovieApi {
    override suspend fun getPopularMovies(): List<MovieDtoItem> {
        return try {
            client.get {
                url(POPULAR)
            }
        } catch (e: RedirectResponseException) {
            emptyList()
        } catch (e: ClientRequestException) {
            emptyList()
        }
    }

    override suspend fun getTrendingMovies(): List<MovieDtoItem> {
        return try {
            client.get {
                url(TRENDING)
            }
        } catch (e: RedirectResponseException) {
            emptyList()
        } catch (e: ClientRequestException) {
            emptyList()
        }
    }

    override suspend fun getTopRatedMovies(): List<MovieDtoItem> {
        return try {
            client.get {
                url(TOP_RATED)
            }
        } catch (e: RedirectResponseException) {
            emptyList()
        } catch (e: ClientRequestException) {
            emptyList()
        }
    }
}
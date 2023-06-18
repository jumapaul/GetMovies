package com.example.getmoview.data.remote

import com.example.getmoview.common.Constants.API_KEY
import com.example.getmoview.common.Constants.POPULAR
import com.example.getmoview.common.Constants.TOP_RATED
import com.example.getmoview.common.Constants.TRENDING
import com.example.getmoview.domain.model.popular_top_rated.MovieDtoItem
import com.example.getmoview.domain.model.trending.TrendingResults
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import javax.inject.Inject

class MovieApiImpl @Inject constructor(private val client: HttpClient) : MovieApi {
    override suspend fun getPopularMovies(): List<MovieDtoItem> {
        return try {
            client.get {
                url(POPULAR)
               headers{
                   append(HttpHeaders.Authorization, "Bearer $API_KEY")
               }
            }
        } catch (e: RedirectResponseException) {
            emptyList()
        } catch (e: ClientRequestException) {
            emptyList()
        }
    }

    override suspend fun getTrendingMovies(): List<TrendingResults> {
        return try {
            client.get {
                url(TRENDING){
                    parameters.append("api_key", API_KEY)
                }
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
                url(TOP_RATED){
                    parameters.append("api_key", API_KEY)
                }
            }
        } catch (e: RedirectResponseException) {
            emptyList()
        } catch (e: ClientRequestException) {
            emptyList()
        }
    }
}
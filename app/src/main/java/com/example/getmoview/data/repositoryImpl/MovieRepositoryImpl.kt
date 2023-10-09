package com.example.getmoview.data.repositoryImpl

import com.example.getmoview.data.local.MoviesDao
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.model.genre.GenreDto
import com.example.getmoview.domain.repository.MovieRepository
import com.example.getmoview.domain.model.movies.MovieDto
import com.example.getmoview.domain.model.MovieDtoItem
import com.example.getmoview.domain.model.top_shows.TvShowDto
import com.example.getmoview.domain.model.top_shows.TvShowItem
import com.example.getmoview.domain.model.upcoming.UpcomingMoviesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MoviesDao
) : MovieRepository {
    // Io dispatchers starts the coroutine tin the IO thread.
    // It is used to perform all the operations such as networking, reading or writing from the database.

    override suspend fun getPopularMovies(): MovieDto {
        return api.getPopularMovies()
    }

    override suspend fun getPopularTvShows(): TvShowDto {
        return api.getPopularTvShows()
    }

    override suspend fun getTopRatedMovies(): MovieDto {
        return withContext(Dispatchers.IO) {
            api.getTopRatedMovies()
        }
    }

    override suspend fun getTopRatedTvShows(page: Int): TvShowDto {
        return withContext(Dispatchers.IO) {
            api.getTopRatedTvShows(page)
        }
    }

    override suspend fun getUpcomingMovies(page: Int): UpcomingMoviesDto {
        return withContext(Dispatchers.IO) {
            api.getUpcomingMovies(page)
        }
    }

    override suspend fun search(query: String): MovieDto {
        return withContext(Dispatchers.IO) {
            api.searchMovie(query)
        }
    }

    override suspend fun getMovieById(id: Int): MovieDtoItem {
        return withContext(Dispatchers.IO) {
            api.getMovieById(id)
        }
    }

    override suspend fun getTvShowsById(id: Int): TvShowItem {
        return withContext(Dispatchers.IO) {
            api.getShowsById(id)
        }
    }

    override suspend fun getMovies(page: Int): MovieDto {
        return withContext(Dispatchers.IO) {
            api.getMovies(page)
        }
    }

    override suspend fun getTvShows(page: Int): TvShowDto {
        return withContext(Dispatchers.IO) {
            api.getShows(page)
        }
    }

    override suspend fun getGenres(): GenreDto {
        return withContext(Dispatchers.IO) {
            api.getMoviesGenre()
        }
    }

    override fun getLocalMovies(): List<MoviesEntity> {
        return dao.getAllMovies()
    }

    override suspend fun getLocalMovieById(id: Int): MoviesEntity {
        return withContext(Dispatchers.IO) {
            dao.getMovieById(id)
        }
    }

    override suspend fun insertMovies(moviesEntity: MoviesEntity) {
        return withContext(Dispatchers.IO){
            dao.addMovies(moviesEntity)
        }
    }

    override suspend fun deleteMovie(moviesEntity: MoviesEntity) {
        return withContext(Dispatchers.IO){
            dao.deleteMovie(moviesEntity)
        }
    }
}
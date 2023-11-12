package com.example.getmoview.data.repositoryImpl

import com.example.getmoview.data.local.CategoryType
import com.example.getmoview.data.local.GenresIdsEntity
import com.example.getmoview.data.local.MoviesDao
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.model.movies.MovieDto
import com.example.getmoview.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MoviesDao
) : MovieRepository {
    // Io dispatchers starts the coroutine in the IO thread.
    // It is used to perform all the operations such as networking, reading or writing from the database.

    //Movies
    override suspend fun saveMovieItems(page: Int) {
        val data = api.getMovies(page)

        dao.addMovies(data.results.map { it.toMovieEntity(CategoryType.MOVIES) })
    }

    override suspend fun getLocalMovies(): List<MoviesEntity> {
        return dao.getAllMovies(CategoryType.MOVIES)
    }

    //Upcoming
    override suspend fun saveUpcomingMovies(page: Int) {
        val data = api.getUpcomingMovies(page)

        dao.addUpcomingMovies(data.results.map { it.toMovieEntity(CategoryType.UPCOMING) })
    }

    override suspend fun getLocalUpcomingMovies(): List<MoviesEntity> {
        return dao.getAllUpcomingMovies(CategoryType.UPCOMING)
    }

    //Top Rated
    override suspend fun saveTopRatedMovies(page: Int) {
        val data = api.getTopRatedMovies(page)
        dao.addTopRatedMovies(data.results.map { it.toMovieEntity(CategoryType.TOP_RATED) })
    }

    override suspend fun getLocalTopRatedMovies(): List<MoviesEntity> {
        return dao.getAllTopRatedMovies(CategoryType.TOP_RATED)
    }

    // Popular Movies
    override suspend fun savePopularMovies(page: Int) {
        val data = api.getPopularMovies(page)

        dao.addPopularMovies(data.results.map { it.toMovieEntity(CategoryType.POPULAR) })
    }

    override suspend fun getLocalPopularMovies(): List<MoviesEntity> {
        return dao.getAllPopularMovies(CategoryType.POPULAR)
    }

    override suspend fun getLocalMovieById(id: Int): MoviesEntity {
        return dao.getMovieById(id)
    }

    override suspend fun search(query: String): MovieDto {
        return api.searchMovie(query)
    }

    // -----------------Shows-------------

    override suspend fun saveShows(page: Int) {
        val data = api.getShows(page)
        dao.addShows(data.results.map { it.toShowEntity(CategoryType.SHOWS) })
    }

    override suspend fun getShows(): List<ShowsEntity> {
        return dao.getShows(CategoryType.SHOWS)
    }

    //Popular Shows
    override suspend fun savePopularShows(page: Int) {
        val data = api.getPopularTvShows(page)

        dao.addPopularShows(data.results.map { it.toShowEntity(CategoryType.POPULAR) })
    }

    override suspend fun getLocalPopularShows(): List<ShowsEntity> {
        return dao.getAllPopularShows(CategoryType.POPULAR)
    }

    //TopRatedShows
    override suspend fun getLocalTopRatedShows(): List<ShowsEntity> {
        return dao.getAllTopRatedShows(CategoryType.TOP_RATED)
    }

    override suspend fun saveTopRatedShows(page: Int) {
        val data = api.getTopRatedTvShows(page)

        dao.addTopRatedShows(data.results.map { it.toShowEntity(CategoryType.TOP_RATED) })
    }

    override suspend fun getTvShowsById(id: Int): ShowsEntity {
        return dao.getShowById(id)
    }

    override suspend fun saveGenres() {
        val data = api.getMoviesGenre()
        dao.addGenres(data.genres.map { it.toGenreIdsEntity() })
    }

    override suspend fun getLocalGenres(): List<GenresIdsEntity> {
        return dao.getGenres()
    }
}
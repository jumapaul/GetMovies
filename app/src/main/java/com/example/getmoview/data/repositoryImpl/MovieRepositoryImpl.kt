package com.example.getmoview.data.repositoryImpl

import com.example.getmoview.data.local.CategoryType
import com.example.getmoview.data.local.GenresIdsEntity
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.data.local.ShowsEntity
import com.example.getmoview.data.local.dao.GenreDao
import com.example.getmoview.data.local.dao.MoviesDao
import com.example.getmoview.data.local.dao.ShowsDao
import com.example.getmoview.data.remote.MovieApi
import com.example.getmoview.domain.model.movies.MovieDto
import com.example.getmoview.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MoviesDao,
    private val showsDao: ShowsDao,
    private val genreDao: GenreDao
) : MovieRepository {
    // Io dispatchers starts the coroutine in the IO thread.
    // It is used to perform all the operations such as networking, reading or writing from the database.

    //Movies
    override suspend fun saveMovieItems(page: Int) {
        val data = api.getMovies(page)

        dao.addMovies(data.results.map {
            it.toMovieEntity(
                CategoryType.MOVIES,
                isFavorite = false
            )
        })
    }

    override suspend fun getLocalMovies(): List<MoviesEntity> {

        return dao.getAllMovies(CategoryType.MOVIES)
    }

    //Upcoming
    override suspend fun saveUpcomingMovies(page: Int) {
        val data = api.getUpcomingMovies(page)

        dao.addUpcomingMovies(data.results.map {
            it.toMovieEntity(
                CategoryType.UPCOMING,
                isFavorite = false
            )
        })
    }

    override suspend fun getLocalUpcomingMovies(): List<MoviesEntity> {
        return dao.getAllUpcomingMovies(CategoryType.UPCOMING)
    }

    //Top Rated
    override suspend fun saveTopRatedMovies(page: Int) {
        val data = api.getTopRatedMovies(page)
        dao.addTopRatedMovies(data.results.map {
            it.toMovieEntity(
                CategoryType.TOP_RATED,
                isFavorite = false
            )
        })
    }

    override suspend fun getLocalTopRatedMovies(): List<MoviesEntity> {
        return dao.getAllTopRatedMovies(CategoryType.TOP_RATED)
    }

    // Popular Movies
    override suspend fun savePopularMovies(page: Int) {
        val data = api.getPopularMovies(page)

        dao.addPopularMovies(data.results.map {
            it.toMovieEntity(
                CategoryType.POPULAR,
                isFavorite = false
            )
        })
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

    // Get favorite movies
    override suspend fun getFavoriteMovies(isFavorite: Boolean): List<MoviesEntity> {
        return dao.getFavoriteMovies(isFavorite)
    }

    override suspend fun updateMoviesList(movie: MoviesEntity) {
        dao.updateMoviesList(movie)
    }

    // -----------------Shows-------------

    override suspend fun saveShows(page: Int) {
        val data = api.getShows(page)
        showsDao.addShows(data.results.map {
            it.toShowEntity(
                CategoryType.SHOWS,
                isFavorite = false
            )
        })
    }

    override suspend fun getLocalShows(): List<ShowsEntity> {
        return showsDao.getShows(CategoryType.SHOWS)
    }

    //Popular Shows
    override suspend fun savePopularShows(page: Int) {
        val data = api.getPopularTvShows(page)

        showsDao.addPopularShows(data.results.map {
            it.toShowEntity(
                CategoryType.POPULAR,
                isFavorite = false
            )
        })
    }

    override suspend fun getLocalPopularShows(): List<ShowsEntity> {
        return showsDao.getAllPopularShows(CategoryType.POPULAR)
    }

    //TopRatedShows
    override suspend fun getLocalTopRatedShows(): List<ShowsEntity> {
        return showsDao.getAllTopRatedShows(CategoryType.TOP_RATED)
    }

    override suspend fun saveTopRatedShows(page: Int) {
        val data = api.getTopRatedTvShows(page)

        showsDao.addTopRatedShows(data.results.map {
            it.toShowEntity(
                CategoryType.TOP_RATED,
                isFavorite = false
            )
        })
    }

    override suspend fun getTvShowsById(id: Int): ShowsEntity {
        return showsDao.getShowById(id)
    }

    override suspend fun saveGenres() {
        val data = api.getMoviesGenre()
        genreDao.addGenres(data.genres.map { it.toGenreIdsEntity() })
    }

    override suspend fun getLocalGenres(): List<GenresIdsEntity> {
        return genreDao.getGenres()
    }

    // Get favorite shows
    override suspend fun getFavoriteShows(isFavorite: Boolean): List<ShowsEntity> {
        return showsDao.getFavoriteShows(isFavorite)
    }

    // Update favorite shows list
    override suspend fun updateShowsList(showsEntity: ShowsEntity) {
        dao.updateShowsList(showsEntity)
    }
}
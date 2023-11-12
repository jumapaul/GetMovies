package com.example.getmoview.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    //Movies
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(moviesEntity: List<MoviesEntity>)
    @Query("SELECT * FROM movies where categoryType = :categoryType")
    fun getAllMovies(categoryType: CategoryType): List<MoviesEntity>

    //Upcoming Movies
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpcomingMovies(moviesEntity: List<MoviesEntity>)

    @Query("SELECT * FROM movies WHERE categoryType = :categoryType")
    fun getAllUpcomingMovies(categoryType: CategoryType): List<MoviesEntity>


    // Popular
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMovies(moviesEntity: List<MoviesEntity>)

    @Query("SELECT * FROM movies WHERE categoryType = :categoryType")
    fun getAllPopularMovies(categoryType: CategoryType): List<MoviesEntity>

    //TopRated
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedMovies(moviesEntity: List<MoviesEntity>)

    @Query("SELECT * FROM movies WHERE categoryType = :categoryType")
    fun getAllTopRatedMovies(categoryType: CategoryType): List<MoviesEntity>

    @Query("SELECT * FROM movies WHERE id=:id")
    suspend fun getMovieById(id: Int): MoviesEntity

    @Delete
    suspend fun deleteMovie(moviesEntity: MoviesEntity)

    // ---------------------Shows--------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShows(showsEntity: List<ShowsEntity>)

    @Query("SELECT * FROM shows WHERE categoryType =:categoryType")
    suspend fun getShows(categoryType: CategoryType): List<ShowsEntity>

    //Top Rated
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedShows(showsEntity: List<ShowsEntity>)

    @Query("SELECT * FROM shows WHERE categoryType =:categoryType")
    fun getAllTopRatedShows(categoryType: CategoryType): List<ShowsEntity>

    //Popular shows
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularShows(showsEntity: List<ShowsEntity>)

    @Query("SELECT * FROM shows WHERE categoryType =:categoryType")
    fun getAllPopularShows(categoryType: CategoryType): List<ShowsEntity>

    @Query("SELECT * FROM shows WHERE id=:id")
    suspend fun getShowById(id: Int): ShowsEntity

    @Delete
    suspend fun deleteShows(showsEntity: ShowsEntity)


    //Genres
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenres(genresIdsEntity: List<GenresIdsEntity>)

    @Query("SELECT * FROM Genres")
    suspend fun getGenres(): List<GenresIdsEntity>
}
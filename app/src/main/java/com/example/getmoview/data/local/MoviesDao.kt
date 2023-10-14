package com.example.getmoview.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MoviesEntity>

    @Query("SELECT * FROM movies WHERE id=:id")
    suspend fun getMovieById(id: Int): MoviesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(moviesEntity: MoviesEntity)

    @Delete
    suspend fun deleteMovie(moviesEntity: MoviesEntity)

    @Query("SELECT * FROM shows")
    fun getAllShows(): List<ShowsEntity>

    @Query("SELECT * FROM shows WHERE id=:id")
    suspend fun getShowById(id: Int): ShowsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShows(showsEntity: ShowsEntity)

    @Delete
    suspend fun deleteShows(showsEntity: ShowsEntity)
}
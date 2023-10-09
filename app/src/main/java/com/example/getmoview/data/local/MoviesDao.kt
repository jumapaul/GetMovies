package com.example.getmoview.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

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
}
package com.example.getmoview.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.getmoview.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM MOVIES")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM MOVIES WHERE ID =:id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: List<MovieEntity>)
}
package com.example.getmoview.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.getmoview.data.local.entity.MovieEntity
import com.example.getmoview.data.local.entity.TrendingMoviesEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM MOVIES")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM MOVIES WHERE ID =:id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: List<MovieEntity>)

    @Query("SELECT * FROM TRENDING")
    fun getAllTrendingMovies(): List<TrendingMoviesEntity>

    @Query("select * from trending where id =:id")
    suspend fun getTrendingById(id: Int): TrendingMoviesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrendingMovies(trendingMoviesEntity: List<TrendingMoviesEntity>)
}
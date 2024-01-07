package com.example.getmoview.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.getmoview.data.local.CategoryType
import com.example.getmoview.data.local.MoviesEntity
import com.example.getmoview.data.local.ShowsEntity

@Dao
interface MoviesDao {

    //Movies
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovies(moviesEntity: List<MoviesEntity>)
    @Query("SELECT * FROM movies where categoryType = :categoryType")
    fun getAllMovies(categoryType: CategoryType): List<MoviesEntity>

    //Upcoming Movies
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUpcomingMovies(moviesEntity: List<MoviesEntity>)

    @Query("SELECT * FROM movies WHERE categoryType = :categoryType")
    fun getAllUpcomingMovies(categoryType: CategoryType): List<MoviesEntity>


    // Popular
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPopularMovies(moviesEntity: List<MoviesEntity>)

    @Query("SELECT * FROM movies WHERE categoryType = :categoryType")
    fun getAllPopularMovies(categoryType: CategoryType): List<MoviesEntity>

    //TopRated
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTopRatedMovies(moviesEntity: List<MoviesEntity>)

    @Query("SELECT * FROM movies WHERE categoryType = :categoryType")
    fun getAllTopRatedMovies(categoryType: CategoryType): List<MoviesEntity>

    @Query("SELECT * FROM movies WHERE id=:id")
    suspend fun getMovieById(id: Int): MoviesEntity

    @Delete
    suspend fun deleteMovie(moviesEntity: MoviesEntity)


    //Get favorite Movies
    @Query("SELECT * FROM movies WHERE isFavorite =:isFavorite")
    suspend fun getFavoriteMovies(isFavorite: Boolean): List<MoviesEntity>

    @Update
    fun updateMoviesList(movie: MoviesEntity)

    @Update
    fun updateShowsList(showsEntity: ShowsEntity)
}
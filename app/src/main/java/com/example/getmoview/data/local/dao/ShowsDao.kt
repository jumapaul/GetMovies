package com.example.getmoview.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.getmoview.data.local.CategoryType
import com.example.getmoview.data.local.ShowsEntity

@Dao
interface ShowsDao{
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

    @Query("SELECT * FROM shows WHERE isFavorite =:isFavorite")
    suspend fun getFavoriteShows(isFavorite: Boolean): List<ShowsEntity>

    @Delete
    suspend fun deleteShows(showsEntity: ShowsEntity)
}
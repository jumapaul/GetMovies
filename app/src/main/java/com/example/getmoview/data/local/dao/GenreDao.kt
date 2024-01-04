package com.example.getmoview.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.getmoview.data.local.GenresIdsEntity

@Dao
interface GenreDao {

    //Genres
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenres(genresIdsEntity: List<GenresIdsEntity>)

    @Query("SELECT * FROM Genres")
    suspend fun getGenres(): List<GenresIdsEntity>
}
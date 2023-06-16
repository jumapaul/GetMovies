package com.example.getmoview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.getmoview.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract val getMovieDao: MovieDao
}
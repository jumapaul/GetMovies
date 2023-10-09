package com.example.getmoview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.getmoview.domain.converters.GenreIdsConverter

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenreIdsConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao
}
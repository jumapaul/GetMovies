package com.example.getmoview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.getmoview.data.local.dao.GenreDao
import com.example.getmoview.data.local.dao.MoviesDao
import com.example.getmoview.data.local.dao.ShowsDao
import com.example.getmoview.domain.converters.GenreIdsConverter

@Database(
    entities = [MoviesEntity::class, ShowsEntity::class, GenresIdsEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(GenreIdsConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao

    abstract val showsDao: ShowsDao

    abstract val genreDao: GenreDao
}
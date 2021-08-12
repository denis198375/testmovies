package com.testmovie.testmovie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.TypeConverters
import androidx.room.RoomDatabase
import com.testmovie.testmovie.data.local.dao.MoviesDao
import com.testmovie.testmovie.data.local.dao.TrailersDao
import com.testmovie.testmovie.data.local.dao.CastsDao
import com.testmovie.testmovie.data.local.dao.ReviewsDao
import com.testmovie.testmovie.data.local.MoviesDatabase
import androidx.room.Room
import com.testmovie.testmovie.data.local.model.*

@Database(
    entities = [Movie::class, Trailer::class, Cast::class, Review::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao?
    abstract fun trailersDao(): TrailersDao?
    abstract fun castsDao(): CastsDao?
    abstract fun reviewsDao(): ReviewsDao?

    companion object {
        const val DATABASE_NAME = "Movies.db"
        private var INSTANCE: MoviesDatabase? = null
        private val sLock = Any()
        @JvmStatic
        fun getInstance(context: Context): MoviesDatabase? {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context)
                }
                return INSTANCE
            }
        }

        private fun buildDatabase(context: Context): MoviesDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
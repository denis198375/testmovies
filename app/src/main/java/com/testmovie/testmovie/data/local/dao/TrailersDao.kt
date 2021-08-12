package com.testmovie.testmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.testmovie.testmovie.data.local.model.Trailer

@Dao
interface TrailersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllTrailers(trailers: List<Trailer?>?)
}
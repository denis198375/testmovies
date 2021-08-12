package com.testmovie.testmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.testmovie.testmovie.data.local.model.Cast

@Dao
interface CastsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCasts(castList: List<Cast?>?)
}
package com.testmovie.testmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.testmovie.testmovie.data.local.model.Review

@Dao
interface ReviewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllReviews(reviews: List<Review?>?)
}
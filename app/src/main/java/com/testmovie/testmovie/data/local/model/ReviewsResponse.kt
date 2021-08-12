package com.testmovie.testmovie.data.local.model

import com.google.gson.annotations.SerializedName

data class ReviewsResponse (

    @SerializedName("results")
    var reviews: List<Review>? = null
)

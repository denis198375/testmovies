package com.testmovie.testmovie.data.local.model

import com.google.gson.annotations.SerializedName

data class TrailersResponse (

    @SerializedName("results")
    var trailers: List<Trailer>? = null
)

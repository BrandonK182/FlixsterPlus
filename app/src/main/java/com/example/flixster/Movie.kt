package com.example.flixster

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null

    @JvmField
    @SerializedName("poster_path")
    var poster: String? = null

    @JvmField
    @SerializedName("vote_average")
    var rating: Float? = null
}
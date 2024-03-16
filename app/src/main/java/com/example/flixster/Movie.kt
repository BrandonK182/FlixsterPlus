package com.example.flixster

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
class Movie : java.io.Serializable {
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
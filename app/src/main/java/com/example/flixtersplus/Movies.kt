package com.example.flixtersplus

import com.google.gson.annotations.SerializedName

data class Movies(

     @SerializedName("poster_path")
     val imageUrl : String ? = null ,

     @SerializedName("title")
     val title : String ? = null,

     @SerializedName("overview")
     val overview : String ? = null,

     @SerializedName("release_date")
     val releaseDate : String ? = null
)

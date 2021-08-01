package com.example.mvvm_mvihiltcwm.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *      Models Data coming from API
 * */

data class BlogNetworkEntity(

    @SerializedName("pk")
    @Expose
    val id: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("body")
    @Expose
    val body: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("category")
    @Expose
    val category: String
)
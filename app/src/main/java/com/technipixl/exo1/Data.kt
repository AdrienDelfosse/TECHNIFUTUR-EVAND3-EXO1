package com.technipixl.exo1


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: String?,
    @SerializedName("limit")
    val limit: String?,
    @SerializedName("offset")
    val offset: String?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("total")
    val total: String?
)
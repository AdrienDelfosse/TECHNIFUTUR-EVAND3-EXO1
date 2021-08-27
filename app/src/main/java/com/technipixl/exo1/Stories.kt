package com.technipixl.exo1


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: String?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<ItemXXX>?,
    @SerializedName("returned")
    val returned: String?
)
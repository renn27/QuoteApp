package com.catnip.quoteapp.data.source.network.model

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class QuoteResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("quote")
    val quote: String?,
    @SerializedName("anime")
    val anime: String?,
    @SerializedName("character")
    val character: String?
)

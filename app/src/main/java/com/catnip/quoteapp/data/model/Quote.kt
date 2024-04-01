package com.catnip.quoteapp.data.model

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class Quote(
    val id: String,
    val quote: String,
    val anime: String,
    val character: String
)

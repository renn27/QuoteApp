package com.catnip.quoteapp.data.mapper

import com.catnip.quoteapp.data.model.Quote
import com.catnip.quoteapp.data.source.network.model.QuoteResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun QuoteResponse.toQuote() = Quote(
    id = this.id.orEmpty(),
    quote = this.quote.orEmpty(),
    anime = this.anime.orEmpty(),
    character = this.character.orEmpty()
)

fun Collection<QuoteResponse>.toQuotes() = this.map {
    it.toQuote()
}
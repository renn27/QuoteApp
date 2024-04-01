package com.catnip.quoteapp.data.datasource

import com.catnip.quoteapp.data.source.network.model.QuoteResponse
import com.catnip.quoteapp.data.source.network.services.QuoteApiServices

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface QuoteDataSource {
    suspend fun getRandomQuotes(): List<QuoteResponse>
}

class QuoteApiDataSource(private val service: QuoteApiServices) : QuoteDataSource {
    override suspend fun getRandomQuotes(): List<QuoteResponse> {
        return service.getRandomQuotes()
    }
}
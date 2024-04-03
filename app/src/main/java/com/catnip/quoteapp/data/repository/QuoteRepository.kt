package com.catnip.quoteapp.data.repository

import com.catnip.quoteapp.data.datasource.QuoteDataSource
import com.catnip.quoteapp.data.mapper.toQuote
import com.catnip.quoteapp.data.mapper.toQuotes
import com.catnip.quoteapp.data.model.Quote
import com.catnip.quoteapp.utils.ResultWrapper
import com.catnip.quoteapp.utils.proceed
import com.catnip.quoteapp.utils.proceedFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface QuoteRepository {
    fun getRandomQuotes() : Flow<ResultWrapper<List<Quote>>>
}
class QuoteRepositoryImpl(private val dataSource: QuoteDataSource) : QuoteRepository{
    override fun getRandomQuotes(): Flow<ResultWrapper<List<Quote>>> {
        return proceedFlow { dataSource.getRandomQuotes().toQuotes() }
    }
}
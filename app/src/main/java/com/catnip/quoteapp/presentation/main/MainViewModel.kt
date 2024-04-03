package com.catnip.quoteapp.presentation.main

import androidx.lifecycle.ViewModel
import com.catnip.quoteapp.data.repository.QuoteRepository
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.catnip.quoteapp.data.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MainViewModel(private val repository: QuoteRepository) : ViewModel() {
    val quotesData = repository.getRandomQuotes().asLiveData(Dispatchers.IO)
}
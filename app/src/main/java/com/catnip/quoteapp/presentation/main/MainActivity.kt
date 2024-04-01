package com.catnip.quoteapp.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.catnip.quoteapp.data.datasource.QuoteApiDataSource
import com.catnip.quoteapp.data.datasource.QuoteDataSource
import com.catnip.quoteapp.data.repository.QuoteRepository
import com.catnip.quoteapp.data.repository.QuoteRepositoryImpl
import com.catnip.quoteapp.data.source.network.services.QuoteApiServices
import com.catnip.quoteapp.databinding.ActivityMainBinding
import com.catnip.quoteapp.utils.GenericViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        val s = QuoteApiServices.invoke()
        val ds: QuoteDataSource = QuoteApiDataSource(s)
        val rp: QuoteRepository = QuoteRepositoryImpl(ds)
        GenericViewModelFactory.create(MainViewModel(rp))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
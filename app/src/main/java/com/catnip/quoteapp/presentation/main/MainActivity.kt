package com.catnip.quoteapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catnip.quoteapp.R
import com.catnip.quoteapp.data.datasource.QuoteApiDataSource
import com.catnip.quoteapp.data.datasource.QuoteDataSource
import com.catnip.quoteapp.data.repository.QuoteRepository
import com.catnip.quoteapp.data.repository.QuoteRepositoryImpl
import com.catnip.quoteapp.data.source.network.services.QuoteApiServices
import com.catnip.quoteapp.databinding.ActivityMainBinding
import com.catnip.quoteapp.presentation.common.adapter.QuoteListAdapter
import com.catnip.quoteapp.utils.GenericViewModelFactory
import com.catnip.quoteapp.utils.proceedWhen

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

    private val adapter: QuoteListAdapter by lazy {
        QuoteListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupList()
        observeData()
    }

    private fun setupList() {
        binding.rvQuote.adapter = adapter
    }

    private fun observeData() {
        viewModel.quotesData.observe(this) { result ->
            result.proceedWhen(
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                    binding.rvQuote.isVisible = false
                },
                doOnSuccess = {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.rvQuote.isVisible = true
                    result.payload?.let { quotes ->
                        adapter.submitData(quotes)
                    }
                },
                doOnError = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = result.exception?.message.orEmpty()
                    binding.rvQuote.isVisible = false
                },
                doOnEmpty = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = getString(R.string.text_quote_is_empty)
                    binding.rvQuote.isVisible = false
                }
            )
        }
    }
}
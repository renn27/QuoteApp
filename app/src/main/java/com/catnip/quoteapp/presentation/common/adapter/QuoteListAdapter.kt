package com.catnip.quoteapp.presentation.common.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.catnip.quoteapp.core.ViewHolderBinder
import com.catnip.quoteapp.data.model.Quote
import com.catnip.quoteapp.databinding.ItemQuoteBinding

class QuoteListAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(
                oldItem: Quote,
                newItem: Quote
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Quote,
                newItem: Quote
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        })

    fun submitData(data: List<Quote>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return QuoteViewHolder(
            ItemQuoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderBinder<Quote>).bind(dataDiffer.currentList[position])
    }

}

class QuoteViewHolder(
    private val binding: ItemQuoteBinding
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Quote> {
    override fun bind(item: Quote) {
        setQuoteData(item)
    }

    private fun setQuoteData(item: Quote) {
        with(binding) {
            tvAnime.text = item.anime
            tvName.text = item.character
            tvQuote.text = item.quote
        }
    }

}
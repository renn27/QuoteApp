package com.catnip.quoteapp.core

interface ViewHolderBinder<T> {
    fun bind(item: T)
}
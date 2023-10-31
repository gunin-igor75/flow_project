package com.github.gunin_igor75.flow_project.crypto_app

import androidx.recyclerview.widget.DiffUtil

object CryptoItemDiffCallback: DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }
}
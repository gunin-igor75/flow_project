package com.github.gunin_igor75.flow_project.crypto_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.gunin_igor75.flow_project.databinding.CryptoItemBinding

class CryptoAdapter : ListAdapter<Currency, CryptoHolder>(CryptoItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val view = CryptoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        val currency = getItem(position)
        with(holder.binding) {
            tvNameCrypto.text = currency.name
            tvPriceCrypto.text = currency.price.toString()
        }
    }
}
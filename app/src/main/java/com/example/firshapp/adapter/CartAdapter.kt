package com.example.firshapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firshapp.databinding.CartItemBinding
import com.example.firshapp.model.Item

class CartAdapter(
    private val items: List<Item>,
    private val onDeleteClick: (Item) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.cartFood.text = item.name
            binding.cartPrice.text = item.harga.toString()
            binding.cartDeskripsi.text = item.deskripsi
            binding.cartTersedia.text = if (item.isAvaible) "Tersedia" else "Tidak Tersedia"
            binding.cartItemQuantity.text = "1" // Default quantity
            binding.deletebtn.setOnClickListener { onDeleteClick(item) }
        }
    }
}
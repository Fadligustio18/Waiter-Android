package com.example.firshapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firshapp.databinding.PopularItemBinding

class PopularAdapter (private val items: List<String>,private val price:List<String>,private val image:List<Int>) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularViewHolder {
        return PopularViewHolder(
            PopularItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PopularViewHolder,
        position: Int
    ) {
        val item = items[position]
        val images = image[position]
        val price = price[position]
        holder.bind(item, price, images)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    class PopularViewHolder(val binding: PopularItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}

private
fun PopularAdapter.PopularViewHolder.bind(item: String, price: String, images: Int) {
    binding.foodnamepopular.text = item
    binding.imageView.setImageResource(images)
    binding.harga.text = price
}

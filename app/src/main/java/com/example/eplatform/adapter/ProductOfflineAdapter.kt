package com.example.eplatform.adapter

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eplatform.R
import com.example.eplatform.databinding.ProductItemBinding
import com.example.eplatform.network.model.ProductResponse

class ProductOfflineAdapter(product: ArrayList<ProductResponse.ProductResItem>) :
    RecyclerView.Adapter<ProductOfflineAdapter.MyViewHolder>() {

    var productOffline: MutableList<ProductResponse.ProductResItem> = mutableListOf()


    init {
        productOffline.addAll(product)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productOffline.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = productOffline[position]
        holder.binding.data = item


        holder.binding.price.text = item.price.toString()
        holder.binding.price.paintFlags = holder.binding.price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        holder.binding.discountedPrice.text =item.price.toString()
        holder.binding.wishlist.setOnClickListener {
            if (item != null) {
                holder.binding.wishlist.setImageResource(R.drawable.ic_color_favourite)
            }
        }


        Log.d("adapter_imagees", "onBindViewHolder: ${item.images[0]}")

        Glide.with(holder.binding.image.context)
            .load(item.images[0])
            .into(holder.binding.image)
    }


}
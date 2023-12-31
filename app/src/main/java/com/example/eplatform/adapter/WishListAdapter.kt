package com.example.eplatform.adapter

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eplatform.R
import com.example.eplatform.db.entities.WishListEntity
import com.example.eplatform.paging.ItemClickListener

class WishListAdapter(val view: Int, private val clickListener: itemClickListener) : BaseAdapter<WishListEntity>(view) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val item = list[position]
        view.apply {
            val priceTv = findViewById<TextView>(R.id.price)
            priceTv.text = "${item.price}"
            findViewById<TextView>(R.id.discountPrice).text = item.price.toString()
        }

        val imageUrl = item.image
        if (imageUrl != null) {

            Log.d("imageUrl", "onBindViewHolder: iamge  $imageUrl")
            Glide.with(view)
                .load(imageUrl)
                .into(view.findViewById<ImageView>(R.id.productImage))
        }

        view.apply {

            findViewById<ImageView>(R.id.delete).setOnClickListener {
                clickListener.onItemDeleted(item,position)
            }
            findViewById<ImageView>(R.id.addcart).setOnClickListener {
                clickListener.onItemDeleted(item,position)
            }


        }

    }

    interface itemClickListener {
        fun onItemDeleted(item: WishListEntity, position: Int)
        fun onAddToCart(item: WishListEntity, position: Int)
    }


}
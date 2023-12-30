package com.example.eplatform.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eplatform.R
import com.example.eplatform.db.entities.WishListEntity

class WishListAdapter(val view: Int): BaseAdapter<WishListEntity>(view) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val item = list[position]
        view.apply {
            val priceTv = findViewById<TextView>(R.id.price)
            priceTv.text = "${item.price}"
            findViewById<TextView>(R.id.discountedPrice).text = "${item.price}"
        }
    }



}
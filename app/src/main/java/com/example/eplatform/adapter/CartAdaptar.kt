package com.example.eplatform.adapter

import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eplatform.R
import com.example.eplatform.databinding.ItemCartBinding
import com.example.eplatform.db.entities.CartItemEntity
import com.example.eplatform.ui.viewmodel.CartViewModel

class CartAdaptar(private val clickListener: OnItemClickListener) :
    BaseAdapter<CartItemEntity>(R.layout.item_cart) {

    var currentValue = MutableLiveData<Int>()


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

        //setItemCount(view,item,position)

        view.apply {

            findViewById<ImageView>(R.id.delete).setOnClickListener {
                clickListener.onItemDeleted(item, position)
            }

            findViewById<ImageView>(R.id.plus).setOnClickListener {
                val current = findViewById<TextView>(R.id.count)
                val currentValue = current.text.toString().toIntOrNull()
                Log.d("qty_db", "onBindViewHolder: qty plus: $currentValue")
                if (currentValue != null && currentValue < 10) {
                    val updatedValue = currentValue + 1
                    current.text = "$updatedValue"
                    item.qty = updatedValue
                    clickListener.updateItem(item)
                    findViewById<TextView>(R.id.total).text =
                        (updatedValue * item.price!!).toString()
                }

            }
            findViewById<ImageView>(R.id.minus).setOnClickListener {
                val currentTv = findViewById<TextView>(R.id.count)
                val currentValue = currentTv.text.toString().toIntOrNull()
                Log.d("qty_db", "onBindViewHolder: qty minus: $currentValue")
                if (currentValue != null && currentValue > 1) {
                    val updatedValue = currentValue - 1
                    currentTv.text = "$updatedValue"
                    item.qty = updatedValue
                    clickListener.updateItem(item)
                    findViewById<TextView>(R.id.total).text =
                        (updatedValue * item.price!!).toString()
                    //currentTv.text = (updatedValue * item.price!!).toString()
                }

            }


        }
    }

    private fun updateItemCount(type: String, item: CartItemEntity) {
        when (type) {
            "+" -> {

            }
        }
    }




    interface OnItemClickListener {
        fun onItemDeleted(item: CartItemEntity, position: Int)

        fun updateItem(item: CartItemEntity)

    }

}
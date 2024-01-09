package com.example.eplatform.adapter

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eplatform.R
import com.example.eplatform.db.entities.CartItemEntity

class CartAdaptar(private val clickListener: OnItemClickListener) :
    BaseAdapter<CartItemEntity>(R.layout.item_cart) {

    var currentValue = MutableLiveData<Int>()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val item = list[position]

//
//        view.findViewById<TextView>(R.id.total).text = item.price.toString()

        view.apply {
            val title = findViewById<TextView>(R.id.productName)
            val priceTv = findViewById<TextView>(R.id.price)
            val current = findViewById<TextView>(R.id.count)
//            val totalTv = findViewById<TextView>(R.id.total)
//            totalTv.text = "Total ${item.price.toString()}"
            findViewById<TextView>(R.id.total).text = "Total:${ (item.qty * item.price!!).toString()}"
            title.text = item.title.toString()
            priceTv.text = "Price: ${item.price.toString()}"
            current.text = item.qty.toString()
            findViewById<TextView>(R.id.discountPrice).text =
                "Discount Price: ${item.price.toString()}"
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
                val currentValue = item.qty
                if (currentValue != null && currentValue < 10) {
                    val updatedValue = currentValue + 1



                    item.qty = updatedValue
                    current.text = item.qty.toString()
                    clickListener.updateItem(item)
                    Log.d("checkInc", "onBindViewHolder: ${item.qty}")

                    clickListener.countUpdater(item)
//                    findViewById<TextView>(R.id.total).text =
//                        (updatedValue * item.price!!).toString()
                }
                Log.d("qty_db", "onBindViewHolder: qty plus: $currentValue")

            }
            findViewById<ImageView>(R.id.minus).setOnClickListener {
                val currentTv = findViewById<TextView>(R.id.count)
                val currentValue = item.qty

                Log.d("qty_db", "onBindViewHolder: qty minus: $currentValue")
                if (currentValue != null && currentValue > 1) {
                    val updatedValue = currentValue - 1

                    item.qty = updatedValue
                    clickListener.updateItem(item)
                    currentTv.text = "${item.qty}"
                    clickListener.countUpdater(item)

//                    findViewById<TextView>(R.id.total).text =
//                        (updatedValue * item.price!!).toString()
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

        fun countUpdater(item: CartItemEntity)

    }

}
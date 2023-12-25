package com.example.eplatform.network.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eplatform.databinding.ProductItemBinding
import com.example.eplatform.network.model.ProductResponse

class PagingAdapter() :
    PagingDataAdapter<ProductResponse.ProductResItem, PagingAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)


    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ProductResponse.ProductResItem>() {
                override fun areItemsTheSame(
                    oldItem: ProductResponse.ProductResItem,
                    newItem: ProductResponse.ProductResItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ProductResponse.ProductResItem,
                    newItem: ProductResponse.ProductResItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = getItem(position)
        holder.binding.data = item

        setData(holder, item)
        setImage(holder,item)

    }

    private fun setImage(holder: PagingAdapter.MyViewHolder, item: ProductResponse.ProductResItem?) {
        Glide.with(holder.binding.image.context)
            .load(item?.images?.get(0))
            .into(holder.binding.image)

    }

    private fun setData(holder: PagingAdapter.MyViewHolder, item: ProductResponse.ProductResItem?) {

        with(holder.binding) {
            price.text =  "${item?.price}"
            discountedPrice.text = "0 dollar"
            saveamount.text="0 dollar"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }



}
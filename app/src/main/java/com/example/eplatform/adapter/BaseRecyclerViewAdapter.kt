package com.example.eplatform.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position in items.indices) {
            (holder as Binder<T>).bind(items[position])
        }

    }

    override fun getItemCount(): Int = items.size

    interface Binder<in T> {
        fun bind(item: T)
    }
}
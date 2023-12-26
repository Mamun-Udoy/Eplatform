package com.example.eplatform.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T: Any>(
    @LayoutRes private val view:Int
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list:ArrayList<T> = arrayListOf()
    private val parent:ViewGroup? = null
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}
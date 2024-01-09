package com.example.eplatform.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any>(
    @LayoutRes private val view: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val list: ArrayList<T> = arrayListOf()
    var counter: Int = 0
    fun addItem(item: T) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun addNewList(newList: List<T>?) {
        if (newList == null) return
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun eachItemCount(count: Int): Int{
        counter = count
        return counter
    }

    fun addNewArrayList(newList: ArrayList<T>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(view,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    abstract override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)


}
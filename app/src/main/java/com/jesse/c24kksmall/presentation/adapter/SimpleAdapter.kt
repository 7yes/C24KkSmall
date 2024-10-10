package com.jesse.c24kksmall.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jesse.c24kksmall.R

class SimpleAdapter(val lista: List<String>) : RecyclerView.Adapter<SimpleVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SimpleVH(layoutInflater.inflate(R.layout.item_small, parent, false))
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: SimpleVH, position: Int) {
       holder.bind(lista[position])
    }

}
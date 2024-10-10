package com.jesse.c24kksmall.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jesse.c24kksmall.databinding.ItemSmallBinding
import com.jesse.c24kksmall.load

class SimpleVH(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSmallBinding.bind(view)
    fun bind(s: String) {
        binding.iv.load(s)
    }
}
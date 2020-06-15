package com.rahulshah456.audioplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulshah456.audioplayer.databinding.ItemSongCategoryBinding
import com.rahulshah456.audioplayer.models.SongCategory
import com.rahulshah456.audioplayer.adapter.common.CustomViewHolder

class SongCategoryAdapter : ListAdapter<SongCategory, CustomViewHolder>(Companion) {

    private val viewPool = RecyclerView.RecycledViewPool()

    companion object : DiffUtil.ItemCallback<SongCategory>() {
        override fun areItemsTheSame(oldItem: SongCategory, newItem: SongCategory): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SongCategory, newItem: SongCategory): Boolean {
            return  oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSongCategoryBinding.inflate(inflater, parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentBookCategory = getItem(position)
        val itemBinding = holder.binding as ItemSongCategoryBinding

        itemBinding.songCategory = currentBookCategory
        itemBinding.nestedRecyclerView.setRecycledViewPool(viewPool)
        itemBinding.executePendingBindings()
    }
}
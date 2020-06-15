package com.rahulshah456.audioplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rahulshah456.audioplayer.adapter.common.CustomViewHolder
import com.rahulshah456.audioplayer.databinding.ItemSongBinding
import com.rahulshah456.audioplayer.models.Song

class SongAdapter : ListAdapter<Song, CustomViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return  oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSongBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentSong = getItem(position)
        val itemBinding = holder.binding as ItemSongBinding
        itemBinding.song = currentSong
        itemBinding.executePendingBindings()
    }
}
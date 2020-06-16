package com.rahulshah456.audioplayer.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulshah456.audioplayer.databinding.ItemSongCategoryBinding
import com.rahulshah456.audioplayer.models.SongCategory
import com.rahulshah456.audioplayer.adapter.common.CustomViewHolder
import com.rahulshah456.audioplayer.models.Song
import kotlinx.android.synthetic.main.activity_main.*

class SongCategoryAdapter : ListAdapter<SongCategory, CustomViewHolder>(Companion) {

    private val viewPool = RecyclerView.RecycledViewPool()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, thumbnail: View, song: Song)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

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

        val songAdapter = SongAdapter()
        songAdapter.submitList(currentBookCategory.songs)
        songAdapter.setOnItemClickListener(object : SongAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, thumbnail: View, song: Song) {
                onItemClickListener?.onItemClick(position,thumbnail,song)
            }
        })
        itemBinding.songCategory = currentBookCategory
        itemBinding.nestedRecyclerView.setRecycledViewPool(viewPool)
        itemBinding.nestedRecyclerView.adapter = songAdapter
        itemBinding.nestedRecyclerView.setItemViewCacheSize(20)
        itemBinding.nestedRecyclerView.setHasFixedSize(true)
        itemBinding.nestedRecyclerView.isDrawingCacheEnabled = true
        itemBinding.nestedRecyclerView.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        itemBinding.executePendingBindings()
    }

    override fun onViewRecycled(holder: CustomViewHolder) {
        holder.binding.executePendingBindings();
        super.onViewRecycled(holder)
    }
}
package com.rahulshah456.audioplayer.adapter.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulshah456.audioplayer.models.Song
import com.rahulshah456.audioplayer.adapter.SongAdapter

@BindingAdapter(value = ["setSongs"])
fun RecyclerView.setSongs(songs: List<Song>?) {
    if (songs != null) {
        val songAdapter = SongAdapter()
        songAdapter.submitList(songs)
        adapter = songAdapter
    }
}
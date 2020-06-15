package com.rahulshah456.audioplayer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class SongCategory(
        val id: String,
        val title: String,
        val songs: List<Song>
)
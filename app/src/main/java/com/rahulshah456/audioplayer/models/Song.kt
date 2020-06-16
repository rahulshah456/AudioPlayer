package com.rahulshah456.audioplayer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song (
        var id : String,
        var songName: String,
        var artistName: String,
        var coverImage: Int,
        var songPath: Int
): Parcelable
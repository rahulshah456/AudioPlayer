package com.rahulshah456.audioplayer.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.rahulshah456.audioplayer.R
import com.rahulshah456.audioplayer.adapter.SongCategoryAdapter
import com.rahulshah456.audioplayer.models.Song
import com.rahulshah456.audioplayer.models.SongCategory
import com.rahulshah456.audioplayer.utils.Constants.COVER_TAG
import com.rahulshah456.audioplayer.utils.Constants.Categories
import com.rahulshah456.audioplayer.utils.Constants.DRAWABLE
import com.rahulshah456.audioplayer.utils.Constants.artists
import com.rahulshah456.audioplayer.utils.Constants.songs
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStatusBarColor()

        val songCategoryAdapter = SongCategoryAdapter()
        songCategoryAdapter.submitList(getData())
        rv_main.adapter = songCategoryAdapter
    }


    private fun getData(): ArrayList<SongCategory> {
        val bookCategory = arrayListOf<SongCategory>()
        for (catId in 1..8) {
            val songList = arrayListOf<Song>()
            for(songId in 0 until 5) {
                val random = Random.nextInt(0, 16)
                val drawable = getResourceId( COVER_TAG + "$random", DRAWABLE, packageName)
                val book = Song("$songId", songs.random().toString(),artists.random().toString(), drawable,0)
                songList += book
            }
            bookCategory += SongCategory("$catId", Categories.random(), songList)
        }
        return bookCategory
    }

    private fun initStatusBarColor() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.colorPrimaryDark)
    }
    private fun getResourceId(variableName: String?, resourceName: String?, packageName: String?): Int {
        return try {
            resources.getIdentifier(variableName, resourceName, packageName)
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }


}
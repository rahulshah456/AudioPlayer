package com.rahulshah456.audioplayer.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
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
        songCategoryAdapter.setOnItemClickListener(object : SongCategoryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, thumbnail: View, song: Song) {
                val intent = Intent(this@MainActivity, PlayerActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("transitionName", position)
                intent.putExtra("song", song)
                val p1 = Pair.create(thumbnail, thumbnail.transitionName)
                val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(this@MainActivity, p1)
                startActivity(intent, options.toBundle())
            }
        })
        rv_main.adapter = songCategoryAdapter
        rv_main.setItemViewCacheSize(20)
        rv_main.setHasFixedSize(true)
        rv_main.isDrawingCacheEnabled = true
        rv_main.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
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
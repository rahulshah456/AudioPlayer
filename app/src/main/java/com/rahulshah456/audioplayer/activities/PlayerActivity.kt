package com.rahulshah456.audioplayer.activities

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rahulshah456.audioplayer.R
import com.rahulshah456.audioplayer.databinding.ActivityPlayerBinding
import com.rahulshah456.audioplayer.utils.OnSwipeTouchListener
import com.rahulshah456.audioplayer.viewModel.AudioViewModel


class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var viewModel: AudioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()

        //viewModel init
        viewModel  = ViewModelProvider(this).get(AudioViewModel::class.java)

        // binding layout views
        initViewBinding()
        setContentView(binding.root)


        //init startup essentials
        initStatusBarColor()
        Handler().postDelayed({
            startPostponedEnterTransition()
        },250)


        listeners()
    }


    //change color of statusBar
    private fun initStatusBarColor() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.splash_background)
    }
    //binding views with the UI Components
    private fun initViewBinding(){
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        binding.song = intent.getParcelableExtra("song")
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.coverImage.isEnabled = false
    }


    //adding observer and swipe listeners
    private fun listeners(){
        viewModel.isPlaying.observe(this, Observer {
            binding.playPauseView.change(!it,true)
            binding.coverImage.performClick()
        })
        binding.clMain.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            override fun onSwipeRight() {
                onBackPressed()
            }
            override fun onSwipeLeft() {
                onBackPressed()
            }
        })
        viewModel.mProgress.observe(this, Observer {
            binding.progressCircular.progress = it
        })

    }


    override fun onStop() {
        try {
            if (viewModel.mediaPlayer.isPlaying){
                viewModel.closePlayer()
            }
        } catch (e:Exception){
            e.printStackTrace()
        }
        super.onStop()
    }


}
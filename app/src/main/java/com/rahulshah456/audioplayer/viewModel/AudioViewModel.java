package com.rahulshah456.audioplayer.viewModel;

import android.app.Application;
import android.media.MediaPlayer;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.rahulshah456.audioplayer.R;

public class AudioViewModel extends AndroidViewModel {

    public MutableLiveData<Float> mProgress = new MutableLiveData<>();
    public MutableLiveData<Boolean> isPlaying = new MutableLiveData<>();
    private Application application;
    public MediaPlayer mediaPlayer;

    public AudioViewModel(@NonNull Application application) {
        super(application);

        this.application = application;
        isPlaying.setValue(false);
        mProgress.setValue(0f);
        mediaPlayer = MediaPlayer.create(application, R.raw.patriot_act_netflix);
        mediaPlayer.setVolume(50f, 50f);
    }

    //mediaPlayer Player
    public void onClicked(){
        if (mediaPlayer.isPlaying()){
            pauseMusic();
        } else playMusic();
    }


    //play pause and close musicPlayer
    private void  playMusic(){
        try {
            if (mediaPlayer==null){
                mediaPlayer = MediaPlayer.create(application, R.raw.patriot_act_netflix);
            }
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    isPlaying.setValue(false);
                }
            });
            mProgressUpdateHandler.postDelayed(mUpdateProgress, 0);
            updateStatus(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void  pauseMusic(){
        mediaPlayer.pause();
        updateStatus(false);
    }
    public void closePlayer(){
        mProgressUpdateHandler.removeCallbacks(mUpdateProgress);
        mediaPlayer.setOnCompletionListener(null);
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        mProgress.setValue(0f);
    }


    //updates the status of music player
    private void updateStatus(Boolean status){
        isPlaying.setValue(status);
    }


    //handler for mediaPlayer progress
    private Handler mProgressUpdateHandler = new Handler();
    private Runnable mUpdateProgress = new Runnable() {
        @Override
        public void run() {
            float progress = ((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100;
            mProgress.setValue(progress);
            mProgressUpdateHandler.postDelayed(this, 150);
        }
    };
}

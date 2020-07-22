package com.Zopherus.mykingdomappv100

import android.app.Activity
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView

class AdVideoActivity : Activity() {

    private lateinit var adPlayerView: VideoView

    //Saving current state
    private var mSavingPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ad_video_player)

        //The video player
        adPlayerView = findViewById(R.id.adPlayerView)

        //Media Controller
        val mediaController = MediaController(this)
        adPlayerView.setMediaController(mediaController)
        mediaController.setAnchorView(adPlayerView)

        //End activity when video finishes
        adPlayerView.setOnCompletionListener { finish() }
    }

    //Initializing player
    private fun initializePlayer() {
        //Uri
        val videoPath:String = "android.resource://" + packageName + "/" + R.raw.boomplayad
        val uri:Uri = Uri.parse(videoPath)
        adPlayerView.setVideoURI(uri)

        //Check where to play
        if (mSavingPosition > 0) {
            adPlayerView.seekTo(mSavingPosition);
        } else {
            // Skipping to 1 shows the first frame of the video
            adPlayerView.seekTo(1);
        }

        //Start playing video
        adPlayerView.start()
    }

    //Release resources
    private fun releasePlayer() {
        adPlayerView.stopPlayback()
    }

    //Start
    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    //Back to home when app in background
    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    //Pause
    override fun onPause() {
        super.onPause()
        mSavingPosition = adPlayerView.getCurrentPosition()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            adPlayerView.pause();
        }
    }

    //Release all video resources on close/exit
    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    //For no skipping
    /*override fun onBackPressed() {
        //Do nothing
    }*/
}
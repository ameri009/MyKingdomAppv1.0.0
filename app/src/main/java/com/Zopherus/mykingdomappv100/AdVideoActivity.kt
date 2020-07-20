package com.Zopherus.mykingdomappv100

import android.app.Activity
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class AdVideoActivity : Activity() {

    private lateinit var adPlayerView: VideoView

    private var mCurrentPosition = 0
    private val PLAYBACK_TIME = "play_time"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ad_video_player)

        //The video player
        adPlayerView = findViewById(R.id.adPlayerView)

        //Check for save state
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }

        //Media Controller
        val mediaController: MediaController = MediaController(this)
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

        //Check where to play or continue playing
        if (mCurrentPosition > 0) {
            adPlayerView.seekTo(mCurrentPosition);
        } else {
            // Skipping to 1 shows the first frame of the video.
            adPlayerView.seekTo(1);
        }

        //Start playing video
        adPlayerView.start()
    }

    //Release resources
    private fun releasePlayer() {
        adPlayerView.stopPlayback()
    }

    //To save state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PLAYBACK_TIME, adPlayerView.getCurrentPosition())
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

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            adPlayerView.pause();
        }
    }

    //Release all video resources on close/exit
    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    /*override fun onBackPressed() {
        //Do nothing
    }*/
}
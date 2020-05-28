package com.example.mykingdomappv100

import android.app.Activity
import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.live_video_player.*

class VideoActivity : Activity() {

    //Initializing videoView
    private lateinit var vidView:VideoView

    //URL of channel(s): Testing URL
    private val videoURL:String = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"
    //Testing for
    //private val videoURL:String = "https://law.duke.edu/cspd/contest/videos/Framed-Contest_Documentaries-and-You.mp4"

    //Add Progressbar!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_video_player)

        //VideoView is VideoView
        vidView = findViewById(R.id.videoView)

        //Video Play
        playVideo()
    }

    private fun playVideo() {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT)

            //Set Media Controller
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            //Url to Uri
            val videoUri:Uri = Uri.parse(videoURL)
            //Set Media Controllerto videoview
            vidView.setMediaController(mediaController)
            //Set video uri
            vidView.setVideoURI(videoUri)

            vidView.requestFocus()
            vidView.setOnPreparedListener(MediaPlayer.OnPreparedListener {
                @Override
                fun onPrepared(mp:MediaPlayer) {
                    //Start to play video
                    mp.start()
                }
            })
        }
        catch (e:Exception) {
            println("Error: "+e)
        }
    }

}
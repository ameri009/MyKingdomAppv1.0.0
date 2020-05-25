package com.example.mykingdomappv100

import android.app.Activity
import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.MediaController
import android.widget.Toast
import androidx.annotation.Nullable
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.live_video_player.*
import java.lang.Exception

class VideoActivity : Activity() {

    //Initializing videoView
    private lateinit var vidView:VideoView

    //URL of channel(s)
    private val videoURL:String = "https://youtu.be/5qap5aO4i9A"


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
            println("-----Trying-----")
            getWindow().setFormat(PixelFormat.TRANSLUCENT)
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)

            //Url to Uri
            val videoUri:Uri = Uri.parse(videoURL)
            println("-----URL-----")
            //Set Media Controllerto videoview
            vidView.setMediaController(mediaController)
            //Set video uri
            vidView.setVideoURI(videoUri)
            println("-----Set URL-----")
            vidView.requestFocus()
            vidView.setOnPreparedListener(MediaPlayer.OnPreparedListener {
                @Override
                fun onPrepared(mp:MediaPlayer) {
                    //Start to play video
                    println("-----Starting-----")
                    vidView.start()
                }
            })
        }
        catch (e:Exception) {
            println("Error: "+e)
        }
    }

}
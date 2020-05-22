package com.example.mykingdomappv100

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

class VideoActivity : AppCompatActivity() {

    //URL of channel(s)
    private val videoURL:String = "https://youtu.be/yE4yj26Lg3Y"

    //Add Progressbar!

    override fun onCreate(@Nullable savedInstanceState:Bundle?, @Nullable persistableBundle: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistableBundle)
        setContentView(R.layout.live_video_player)

        //VideoView is VideoView
        val videoView:VideoView = findViewById(R.id.videoView)
        //Video Play
        playVideo()
    }

    private fun playVideo() {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT)
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)

            //Url to Uri
            val videoUri:Uri = Uri.parse(videoURL)
            //Set Media Controllerto videoview
            videoView.setMediaController(mediaController)
            //Set video uri
            videoView.setVideoURI(videoUri)
            videoView.requestFocus()
            videoView.setOnPreparedListener(MediaPlayer.OnPreparedListener {
                @Override
                fun onPrepared(mp:MediaPlayer) {
                    //Start to play video
                    videoView.start()
                }
            })
        }
        catch (e:Exception) {
            Toast.makeText(this, ""+e.message, Toast.LENGTH_SHORT).show()
        }
    }

}
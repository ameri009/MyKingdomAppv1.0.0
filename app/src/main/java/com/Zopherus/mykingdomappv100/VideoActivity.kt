package com.Zopherus.mykingdomappv100

import android.app.Activity
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.DefaultEventListener
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

const val CHANNEL_URL = "channelurl"
const val CHANNEL_NAME = "channelname"

class VideoActivity : Activity() {

    //Initializing the view for the video and video
    private lateinit var playerView:PlayerView
    private lateinit var player:SimpleExoPlayer

    //URL of channel(s): Testing URL
    private lateinit var videoURL:String

    //Add Progressbar!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_video_player)

        //Player view
        playerView = findViewById(R.id.playerView)

        //Get video URL
        videoURL = intent.getStringExtra(CHANNEL_URL)

        //Getting the channel name for title
        val chName: TextView = findViewById<TextView>(R.id.channelTitle)
        chName.setText(intent.getStringExtra(CHANNEL_NAME))

        //Video Play
        playVideo()

        //Back button
        val backButton: Button = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener(View.OnClickListener {
            Log.d("BackButton", "Bye")
            this.finish()
        })
    }

    private fun playVideo() {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT)

            //Set Media Controller
            player = SimpleExoPlayer.Builder(this).build()

            //Url to Uri
            val videoUri:Uri = Uri.parse(videoURL)
            //Set Media Controllerto videoview
            playerView.setPlayer(player)
            playerView.hideController()
            // Produces DataSource instances through which media data is loaded.
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                this,
                Util.getUserAgent(this, "Boom Play")
            )
            // This is the MediaSource representing the media to be played.
            val videoSource: HlsMediaSource = HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(videoUri)
            // Prepare the player with the source.
            player.prepare(videoSource)

            //Adding listener for when playing, pausing, and in-between
            player.addListener(object : DefaultEventListener() {
                override fun onPlayerStateChanged(
                    playWhenReady: Boolean,
                    playbackState: Int
                ) {
                    if (playWhenReady && playbackState == Player.STATE_READY) {
                        // media actually playing
                        window.decorView.systemUiVisibility = hideSystemBars()
                    } else if (playWhenReady) {
                        // might be idle (plays after prepare()),
                        // buffering (plays when data available)
                        // or ended (plays when seek away from end)
                    } else {
                        // player paused in any state
                        //Automatically play
                        //So never pause
                        player.setPlayWhenReady(true)
                        window.decorView.systemUiVisibility = showSystemBars()
                    }
                }
            })
        }
        catch (e:Exception) {
            println("Error: "+e)
        }
    }

    //Back to home when app in background
    override fun onStop() {
        finish()
        super.onStop()
    }

    //Release all video resources on close/exit
    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

    //Flags to show navigation and status bar
    private fun showSystemBars():Int {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    //Flags to hide navigation and status bar
    private fun hideSystemBars():Int {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    //Handle key(dpad) events
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
                playerView.showController()
                true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                playerView.hideController()
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }
}
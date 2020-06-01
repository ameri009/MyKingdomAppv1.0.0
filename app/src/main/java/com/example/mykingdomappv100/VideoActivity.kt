package com.example.mykingdomappv100

import android.app.Activity
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.DefaultEventListener
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


class VideoActivity : Activity() {

    //Initializing the view for the video and video
    private lateinit var playerView:PlayerView
    private lateinit var player:SimpleExoPlayer

    //URL of channel(s): Testing URL
    private val videoURL:String = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"

    //Add Progressbar!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_video_player)

        //Player view
        playerView = findViewById(R.id.playerView)

        //Video Play
        playVideo()
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

            // Produces DataSource instances through which media data is loaded.
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                this,
                Util.getUserAgent(this, "MyKindomApp")
            )
            // This is the MediaSource representing the media to be played.
            val videoSource: MediaSource = HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(videoUri)
            // Prepare the player with the source.
            player.prepare(videoSource)

            //Listener for when controller shows up
            /*playerView.setControllerVisibilityListener { PlayerControlView.VISIBLE
                window.decorView.systemUiVisibility = showSystemBars()
            }*/

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
                        println("PAUSEPAUSEPAUSE")
                        window.decorView.systemUiVisibility = showSystemBars()
                    }
                }
            })
        }
        catch (e:Exception) {
            println("Error: "+e)
        }
    }

    //To pause video when changing app
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        player.setPlayWhenReady(false)
    }

    //Release all video resources on close/exit
    override fun onDestroy() {
        super.onDestroy()
        player.release()
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

    //When resuming
    /*override fun onResume() {
        super.onResume()
        window.decorView.apply {
            systemUiVisibility = hideSystemBars()
        }
    }*/
}
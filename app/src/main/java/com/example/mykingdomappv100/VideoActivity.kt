package com.example.mykingdomappv100

import android.app.Activity
import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


class VideoActivity : Activity() {

    //Initializing the view for the video
    //private lateinit var vidView:VideoView~
    private lateinit var playerView:PlayerView

    //URL of channel(s): Testing URL
    private val videoURL:String = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"
    //Testing for
    //private val videoURL:String = "https://law.duke.edu/cspd/contest/videos/Framed-Contest_Documentaries-and-You.mp4"

    //Add Progressbar!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_video_player)

        //VideoView is VideoView~
        //vidView = findViewById(R.id.videoView)~

        //Player view
        playerView = findViewById(R.id.playerView)

        //Video Play
        playVideo()
    }

    private fun playVideo() {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT)

            //Set Media Controller
            //val mediaController = MediaController(this)~
            //mediaController.setAnchorView(videoView)~
            val player = SimpleExoPlayer.Builder(this).build()

            //Url to Uri
            val videoUri:Uri = Uri.parse(videoURL)
            //Set Media Controllerto videoview
            //vidView.setMediaController(mediaController)~
            playerView.setPlayer(player)
            //Set video uri
            //vidView.setVideoURI(videoUri)~

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
            //Play video
            //player.setPlayWhenReady(true)

            //When donde: !!!!!IMPORTANT!!!!!
            //player.release()

            //vidView.requestFocus()~
            /*vidView.setOnPreparedListener(MediaPlayer.OnPreparedListener {~
                @Override
                fun onPrepared(mp:MediaPlayer) {
                    //Start to play video
                    mp.start()
                }
            })~*/
        }
        catch (e:Exception) {
            println("Error: "+e)
        }
    }

}
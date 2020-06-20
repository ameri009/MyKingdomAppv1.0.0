package com.Zopherus.mykingdomappv100.ui.home

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.Zopherus.mykingdomappv100.R
import com.Zopherus.mykingdomappv100.VideoActivity

//Channel URL pass code
const val CHANNEL_URL = "channelurl"
const val CHANNEL_NAME = "channelname"

/**Channel Names**/
private val channel_names = arrayOf(
    "TVO",
    "VEPACO",
    "PROMAR",
    "TELESUR",
    "ALL SPORTS",
    "AMERICA TV PERU",
    "ANTIESTRESS",
    "ACENTO TV",
    "VOS TV",
    "TV CANARIA",
    "CDM PUERTO RICO",
    "TELEVALENCIA",
    "TV CHILE",
    "CAMPUS TV",
    "CANAL 12 COLOMBIA",
    "OYE TV PANAMA",
    "RCN HD",
    "CANAL ANDALUCIA COCINA",
    "REAL MADRID TV",
    "SER TV",
    "CANAL 11",
    "TELECAFE",
    "TELEENVIGADO",
    "TELEFORMULA",
    "TELEAMAZONAS",
    "TELECARIBE",
    "CANAL SUR",
    "UNICANAL",
    "UNO TV",
    "TELEMADRID"
)

/**Channel URLs**/
private val channel_urls = arrayOf(
    "http://vcp1.myplaytv.com:1935/tvo/tvo/chunklist_w2075785741.m3u8",
    "http://vcp1.myplaytv.com:1935/tvepaco/tvepaco/chunklist_w2066471963.m3u8",
    "http://vcp1.myplaytv.com:1935/promar/promar/playlist.m3u8?",
    "https://d7g1ebft2592.cloudfront.net/mblivev3/hd/playlist.m3u8?",
    "https://5cf4a2c2512a2.streamlock.net/dgrau/dgrau/chunklist.m3u8?",
    "http://cdn.miip.tv:8080/canada/123456/4252?",
    "http://cdn.miip.tv:8080/canada/123456/3810?",
    "https://acentotv01.streamprolive.com/hls/live.m3u8?",
    "http://cdn.miip.tv:8080/canada/123456/177?",
    "http://streaming2.mad.idec.net/rtvcnet/rtvcnet.drb.smil/Playlist.m3u8",
    "https://59825a54e4454.streamlock.net:8443/marcos536/marcos536/playlist.m3u8?",
    "http://streaming.enetres.net/9E9557EFCEBB43A89CEC8FBD3C500247022/live.smil/.m3u8",
    "http://p1.worldkast.com/ebenezertv2/ngrp:ebenezertv2_all/playlist.m3u8?",
    "http://207.38.89.242:8080/live/17299/17299/3673.m3u8?",
    "http://207.38.89.242:8080/live/2993/2993/3673.ts?",
    "https://mdstrm.com/live-stream-playlist/5d88df173853e7072f3f953f.m3u8?",
    "http://cdn.miip.tv:8080/canada/123456/3569?",
    "https://593fa17dec176.streamlock.net//cscocina//cscocina.stream//playlist.m3u8",
    "https://rmtvlive-lh.akamaihd.net/i/rmtv_1@154306/master.m3u8?",
    "https://accionastream.com/live/sertv/playlist.m3u8?",
    "http://207.38.89.242:8080/live/2993/2993/3678.ts?",
    "http://cdn.miip.tv:8080/canada/123456/4246?",
    "http://cdn.miip.tv:8080/canada/123456/4245?",
    "https://wms30.tecnoxia.com/radiof/abr_radioftele/playlist.m3u8?",
    "https://api.new.livestream.com/accounts/1359588/events/4428723/live.m3u8?",
    "https://telecaribe-deportes.gcdn.anvato.net/hls/live/telecaribedeporteshd2/2196k/index.m3u8?",
    "https://cdnlive.shooowit.net/rtvalive/channelDVR.smil/master.m3u8",
    "http://45.55.127.106/live/unicanal.m3u8?",
    "https://ooyalahd2-f.akamaihd.net/i/UnoTV01_delivery@122640/master.m3u8?",
    "http://telemadridhls-live.hls.adaptive.level3.net/telemadrid/telemadrid1/bitrate_1.m3u8"
)

/**Button Array**/
private val vidButtons = mutableListOf<Button>()
private val BUTTON_IDS = arrayOf(
    R.id.videoButton,
    R.id.videoButton2,
    R.id.videoButton3,
    R.id.videoButton4,
    R.id.videoButton5
);


class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        //Implemeting all the buttos
        for(id in BUTTON_IDS) {
            val button = root.findViewById<Button>(id)
            button.setOnClickListener(this)
            buttonEffect(button)
            vidButtons.add(button)
        }

        return root
    }
    //Activity button does on click
    override fun onClick(view: View) {
        val intent = Intent(context, VideoActivity::class.java)
        var numb = 0
        for (x in BUTTON_IDS) {
            if (x == view.id) {
                intent.putExtra(CHANNEL_URL, channel_urls[numb])
                intent.putExtra(CHANNEL_NAME, channel_names[numb])
            }
            numb++
        }
        startActivity(intent)
    }

    //For making a click effect on the button when click
    fun buttonEffect(button: View) {
        button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.background.setColorFilter(-0x1f0b8adf, PorterDuff.Mode.SRC_ATOP)
                    v.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    v.background.clearColorFilter()
                    v.invalidate()
                }
            }
            false
        }
    }

}

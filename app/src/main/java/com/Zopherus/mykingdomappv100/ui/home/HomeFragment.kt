package com.Zopherus.mykingdomappv100.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Zopherus.mykingdomappv100.Channels
import com.Zopherus.mykingdomappv100.R
import com.Zopherus.mykingdomappv100.RecyclerViewAdapter
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

class HomeFragment : Fragment(), RecyclerViewAdapter.OnChannelListener {

    private lateinit var homeViewModel: HomeViewModel

    private val channelList = generateChannelList(channel_names.size)
    private lateinit var recyView:RecyclerView
    private val adapter = RecyclerViewAdapter(channelList, this)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recyView = root.findViewById<RecyclerView>(R.id.recycler_view)
        recyView.adapter = adapter
        recyView.layoutManager = LinearLayoutManager(context)
        recyView.setHasFixedSize(true)

        return root
    }

    //Activity button does on click
    override fun onChannelClick(position: Int) {
        Log.d(tag,"onChannelClick: clicked." + position)
        val intent = Intent(context, VideoActivity::class.java)
        intent.putExtra(CHANNEL_URL, channelList.get(position).channelURL)
        intent.putExtra(CHANNEL_NAME, channelList.get(position).channelName)
        startActivity(intent)
    }

    //Generating the list of channels
    private fun generateChannelList(size: Int): ArrayList<Channels>{

        val list = ArrayList<Channels>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_dashboard_black_24dp
                1 -> R.drawable.ic_notifications_black_24dp
                else -> R.drawable.ic_tv_black_24dp
            }

            val item = Channels(drawable, channel_names[i], channel_urls[i])
            list += item
        }
        return list
    }

}

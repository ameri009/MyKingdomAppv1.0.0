package com.Zopherus.mykingdomappv100.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Zopherus.mykingdomappv100.Channels
import com.Zopherus.mykingdomappv100.R
import com.Zopherus.mykingdomappv100.RecyclerViewAdapter
import com.Zopherus.mykingdomappv100.VideoActivity
import java.lang.reflect.TypeVariable

//Channel URL pass code
const val CHANNEL_URL = "channelurl"
const val CHANNEL_NAME = "channelname"

//Investor
val invest = arrayOf("VEPACO TV", "http://vcp1.myplaytv.com:1935/tvepaco/tvepaco/chunklist_w2066471963.m3u8", R.drawable.vepacotv)

/**Channel Names & URLS**/
private val channel_names_urls = arrayOf(
    arrayOf("TVO", "http://vcp1.myplaytv.com:1935/tvo/tvo/chunklist_w2075785741.m3u8", R.drawable.tvotv),                               //Work
    arrayOf("PROMAR","http://vcp1.myplaytv.com:1935/promar/promar/playlist.m3u8?", R.drawable.promar),                                  //Work
    arrayOf("TELESUR", "https://d7g1ebft2592.cloudfront.net/mblivev3/hd/playlist.m3u8?", R.drawable.telesur),                           //Work
    arrayOf("SPORTV", "https://5cf4a2c2512a2.streamlock.net/dgrau/dgrau/chunklist.m3u8?", R.drawable.sportv),                           //Work
    arrayOf("ACENTO TV", "https://acentotv01.streamprolive.com/hls/live.m3u8?", R.drawable.acentotv),                                   //Work
    arrayOf("TV CANARIA", "http://streaming2.mad.idec.net/rtvcnet/rtvcnet.drb.smil/Playlist.m3u8", R.drawable.tvcanaria),               //Work
    arrayOf("CDM PUERTO RICO", "https://59825a54e4454.streamlock.net:8443/marcos536/marcos536/playlist.m3u8?", R.drawable.cdmtv),       //Work
    arrayOf("TLT", "https://vcp.myplaytv.com/TLTve2/TLTve2/playlist.m3u8", R.drawable.tlt),                                             //TLT
    arrayOf("TV CHILE", "http://p1.worldkast.com/ebenezertv2/ngrp:ebenezertv2_all/playlist.m3u8?", R.drawable.tvchile),                 //Work
    arrayOf("OYE TV PANAMA", "https://mdstrm.com/live-stream-playlist/5d88df173853e7072f3f953f.m3u8?", R.drawable.oyetvpanama),         //Work
    arrayOf("CANAL ANDALUCIA COCINA", "https://593fa17dec176.streamlock.net//cscocina//cscocina.stream//playlist.m3u8", R.drawable.canal_andalucia_cocina), //Work
    arrayOf("REAL MADRID TV", "https://rmtvlive-lh.akamaihd.net/i/rmtv_1@154306/master.m3u8?", R.drawable.real_madrid_tv),              //Work
    arrayOf("SER TV", "https://accionastream.com/live/sertv/playlist.m3u8?", R.drawable.sertv),                                         //Work
    arrayOf("CANAL I", "https://vcp.myplaytv.com/canali/canali/playlist.m3u8", R.drawable.canali),                                      //Canal i
    arrayOf("TELEFORMULA", "https://wms30.tecnoxia.com/radiof/abr_radioftele/playlist.m3u8?", R.drawable.teleformula),                  //Work
    arrayOf("TELECARIBE", "https://telecaribe-deportes.gcdn.anvato.net/hls/live/telecaribedeporteshd2/2196k/index.m3u8?", R.drawable.telecaribe),           //Work
    arrayOf("CANAL SUR", "https://cdnlive.shooowit.net/rtvalive/channelDVR.smil/master.m3u8", R.drawable.canalsur),                     //Work
    arrayOf("UNICANAL", "http://45.55.127.106/live/unicanal.m3u8?", R.drawable.unicanal),                                               //Works: Takes a while to load
    arrayOf("UNO TV", "https://ooyalahd2-f.akamaihd.net/i/UnoTV01_delivery@122640/master.m3u8?", R.drawable.unotv),                     //Video
    arrayOf("TELEMADRID", "http://telemadridhls-live.hls.adaptive.level3.net/telemadrid/telemadrid1/bitrate_1.m3u8", R.drawable.telemadrid)                 //Works
)

class HomeFragment : Fragment(), RecyclerViewAdapter.OnChannelListener {

    private lateinit var homeViewModel: HomeViewModel

    private val channelList = generateChannelList(channel_names_urls.size)
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

    //Setting the toolbar for home
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val toolBarTitle: TextView? = activity?.findViewById<TextView>(R.id.toolBarText)
        toolBarTitle?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 42F)
        toolBarTitle?.setText("BOOM")

        val toolBarLogo: ImageView? = activity?.findViewById<ImageView>(R.id.toolBarIcon)
        toolBarLogo?.visibility = View.VISIBLE
    }

    //Activity button does on click
    override fun onChannelClick(position: Int) {
        //Log.d(tag,"onChannelClick: clicked." + position)
        val intent = Intent(context, VideoActivity::class.java)
        intent.putExtra(CHANNEL_URL, channelList.get(position).channelURL)
        intent.putExtra(CHANNEL_NAME, channelList.get(position).channelName)
        startActivity(intent)
    }

    //Generating the list of channels
    private fun generateChannelList(size: Int): ArrayList<Channels>{

        //Organizing by alphabet
        channel_names_urls.sortBy { it[0].toString() }

        val list = ArrayList<Channels>()

        //Adding invester first
        val item = Channels(invest[2].hashCode(), invest[0].toString(), invest[1].toString())
        list += item

        //Inserting the other channels to recycleview
        for (i in 0 until size) {
            val item = Channels(channel_names_urls[i][2].hashCode(), channel_names_urls[i][0].toString(), channel_names_urls[i][1].toString())
            list += item
        }
        return list
    }

}

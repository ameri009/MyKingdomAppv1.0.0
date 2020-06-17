package com.Zopherus.mykingdomappv100.ui.home

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Zopherus.mykingdomappv100.R
import com.Zopherus.mykingdomappv100.VideoActivity

//Channel URL pass code
const val CHANNEL_URL = "channelurl"

//Channel URLs
const val TVO_URL = "http://vcp1.myplaytv.com:1935/tvo/tvo/chunklist_w2075785741.m3u8"
const val VEPACO_URL = "http://vcp1.myplaytv.com:1935/tvepaco/tvepaco/chunklist_w2066471963.m3u8"

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
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //Button 1 VEPACO
        val vidButton: Button = root.findViewById<Button>(R.id.videoButton)
        vidButton.setOnClickListener(this)
        buttonEffect(vidButton)

        //Button 2 TVO
        val vidButton2: Button = root.findViewById<Button>(R.id.videoButton2)
        vidButton2.setOnClickListener(this)
        buttonEffect(vidButton2)

        return root
    }
    //Activity button does on click
    override fun onClick(view: View) {
        val intent = Intent(context, VideoActivity::class.java)
        when (view.id) {
            R.id.videoButton -> {intent.putExtra(CHANNEL_URL, VEPACO_URL)}
            R.id.videoButton2 -> {intent.putExtra(CHANNEL_URL, TVO_URL)}
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

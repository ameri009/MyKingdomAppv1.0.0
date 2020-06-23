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
import androidx.recyclerview.widget.LinearLayoutManager
import com.Zopherus.mykingdomappv100.Channels
import com.Zopherus.mykingdomappv100.R
import com.Zopherus.mykingdomappv100.RecyclerViewAdapter
import com.Zopherus.mykingdomappv100.VideoActivity
import kotlinx.android.synthetic.main.fragment_home.*

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
//TO-DO        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        //Button in Home
/*        val vidButton: Button = root.findViewById<Button>(R.id.videoButton)
        vidButton.setOnClickListener(this)
        buttonEffect(vidButton) */
        val channelList = generateDummyList(30)

        recycler_view.adapter = RecyclerViewAdapter(channelList)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)


        return root
    }



    //Activity button does on click
    override fun onClick(view: View) {
        val intent = Intent(context, VideoActivity::class.java)
        startActivity(intent)
    }

    //For making a click effect on the button when click
    /* TO DO
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
    } */

    private fun generateDummyList(size: Int): List<Channels>{

        val list = ArrayList<Channels>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_dashboard_black_24dp
                1 -> R.drawable.ic_notifications_black_24dp
                else -> R.drawable.ic_tv_black_24dp
            }

            val item = Channels(drawable, "Item $i", "URL")
            list += item
        }
        return list
    }

}

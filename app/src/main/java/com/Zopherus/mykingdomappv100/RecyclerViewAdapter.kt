package com.Zopherus.mykingdomappv100

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.channel_item.view.*

class RecyclerViewAdapter(private val channelList: ArrayList<Channels>, private val mOnChannelListener: OnChannelListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.channel_item, parent, false)

        return RecyclerViewHolder(itemView, mOnChannelListener)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = channelList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.channelNameView.text = currentItem.channelName
        holder.channelURLView.text = currentItem.channelURL
    }

    override fun getItemCount() = channelList.size

    class RecyclerViewHolder(itemView: View, onChannelListener: OnChannelListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.image_view
        val channelNameView: TextView = itemView.channelName_view
        val channelURLView: TextView = itemView.channelURL_view
        lateinit var onChannelListener: OnChannelListener
        init {
            this.onChannelListener = onChannelListener
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onChannelListener.onChannelClick(adapterPosition)
        }
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

    public interface OnChannelListener {
        fun onChannelClick(position: Int)
    }
}
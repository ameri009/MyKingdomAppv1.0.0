package com.Zopherus.mykingdomappv100.ui.settings

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Zopherus.mykingdomappv100.R

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        settingsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    //Setting the toolbar for favorites
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val toolBarTitle: TextView? = activity?.findViewById<TextView>(R.id.toolBarText)
        toolBarTitle?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28F)
        toolBarTitle?.setText("Settings")

        val toolBarLogo: ImageView? = activity?.findViewById<ImageView>(R.id.toolBarIcon)
        toolBarLogo?.visibility = View.INVISIBLE
    }
}

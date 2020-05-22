package com.example.mykingdomappv100

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_settings))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /*Adding video button and its function when in home Tab xxx
        try {
            val vidButton: Button = findViewById<Button>(R.id.videoButton)
            vidButton.setOnClickListener(View.OnClickListener {
                @Override
                fun onClick(view: View) {
                    val intent = Intent(getApplicationContext(), VideoActivity::class.java)
                    startActivity(intent)
                }
            })
        }
        catch (e:Exception) {
            println("NOOOOOPPPPEEE")
        }*/

    }
}

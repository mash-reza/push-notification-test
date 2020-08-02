package com.festive.pushnotificationtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        stop.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

    }
}

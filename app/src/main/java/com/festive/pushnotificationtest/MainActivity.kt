package com.festive.pushnotificationtest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var broadcastReceiver: BroadcastReceiver
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val message = intent!!.getStringExtra("message")
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Messege from heaven!")
                    .setMessage(message)
                    .setPositiveButton("OK, Fuck OFF Man!") { _, _ -> }
                    .create()
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(MyFirebaseMessegingService.INTENT_ACTION_SEND_MESSAGE)
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,filter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }
}

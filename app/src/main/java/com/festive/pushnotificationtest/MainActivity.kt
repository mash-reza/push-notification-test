package com.festive.pushnotificationtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var TEXT: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                getResult1FromApi()
                withContext(Dispatchers.Main){
                    textView.text = TEXT
                }
            }
        }

    }

    private suspend fun getResult1FromApi() {
        delay(2000)
        TEXT = "Result 1"
        logThread("getResult1FromApi")
    }

    private suspend fun logThread(methodName: String) {
        Log.d(TAG, "method name: $methodName in thread: ${Thread.currentThread().name}")
    }
}

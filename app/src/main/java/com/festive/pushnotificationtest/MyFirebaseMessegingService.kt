package com.festive.pushnotificationtest

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessegingService : FirebaseMessagingService() {

    companion object {
        val LOGTAG = "MyFirebaseMesseging"
        val INTENT_ACTION_SEND_MESSAGE = "INTENT_ACTION_SEND_MESSAGE"
    }

    override fun onNewToken(p0: String) {
        Log.d(LOGTAG, "refreshed token is $p0")
        val registerWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<RegisterWorker>().addTag("register")
            .setInputData(Data.Builder().putString("token", p0).build()).build()
        WorkManager.getInstance(this).enqueue(registerWorkRequest)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val messege = p0.data["message"]
        passMessageToActivity(messege)
    }

    private fun passMessageToActivity(messege: String?) {
        val intent: Intent = Intent().apply {
            action = INTENT_ACTION_SEND_MESSAGE
            putExtra("message", messege)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}
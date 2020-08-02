package com.festive.pushnotificationtest

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.annotation.RequiresApi

class MyService : Service() {

    lateinit var player: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        player.start()
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }
        var CHANNEL_ID =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                createNotificationChannel("my_music_player_service", "My Music player Service")
            else ""
        val notification =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("Music player")
                    .setContentText("Ringtone is playing...")
                    .setSmallIcon(R.drawable.image)
                    .setContentIntent(pendingIntent)
                    .setTicker("Ticker text music is playing")
                    .build()
            else {
                Notification.Builder(this)
                    .setContentTitle("Music player")
                    .setContentText("Ringtone is playing...")
                    .setSmallIcon(R.drawable.image)
                    .setContentIntent(pendingIntent)
                    .setTicker("Ticker text music is playing")
                    .build()
            }

        startForeground(100, notification)
        return START_STICKY
    }

    override fun onDestroy() {
        player.stop()
        player.release()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}
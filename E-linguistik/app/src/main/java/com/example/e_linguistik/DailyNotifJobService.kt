package com.example.e_linguistik

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class DailyNotifJobService: JobService() {
    override fun onStartJob(p0: JobParameters): Boolean {
        Log.d(TAG, "onStartJob()")
        showNotification(p0, applicationContext, 100)
        return true
    }

    private fun showNotification(job: JobParameters, context: Context,notifId: Int) {
        val channelId = "Channel_3"
        val channelName = "E-Linguistik channel"
        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Reminder")
            .setContentText("Jangan lupa untuk menambah kosakata")
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT)
            builder.setChannelId(channelId)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManagerCompat.notify(notifId, notification)
        jobFinished(job,false)
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob()")
        return true
    }
}
package com.ndv.workmanagerexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

const val JOKE_KEY = "jokeKey"
const val JOKE_TITLE_KEY = "title"
const val NOTIFICATION_ID = 66
const val NOTIFICATION_NAME = "jokeWorker"
const val NOTIFICATION_CHANNEL = "jokeWorker_channel_01"

class JokeWorker(private val appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        showNotification(NOTIFICATION_ID)
        return Result.success()
    }

    private fun showNotification(id: Int) {
        val actionPendingIntent = preparePendingIntent()
        val notificationManager =
            appContext.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        val notification = prepareNotification(actionPendingIntent)

        createNotificationChannel(notificationManager)
        notificationManager.notify(id, notification)
    }

    private fun preparePendingIntent(): PendingIntent {
        val actionIntent = Intent(appContext, AfterClickNotificationActivity::class.java)
        return PendingIntent.getActivity(appContext,
            0,
            actionIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel =
            NotificationChannel(NOTIFICATION_CHANNEL,
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }

    private fun prepareNotification(actionPendingIntent: PendingIntent): Notification {
        val builder =
            NotificationCompat.Builder(appContext, NOTIFICATION_CHANNEL)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(inputData.getString(JOKE_TITLE_KEY)!!)
                .setContentText(inputData.getString(JOKE_KEY)!!)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(actionPendingIntent)

        return builder.build()
    }
}

package com.rr.lazylist.ui.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.rr.lazylist.MainActivity
import com.rr.lazylist.R

object LazyNotificationService {
    private const val CHANNEL_ID = "lazylist_reminders"
    private const val CHANNEL_NAME = "LazyList Reminders"

    fun createNotificationChannel(context: Context) {
        val notificationManager = getSystemService(
            context,
            NotificationManager::class.java
        )

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminders for your lazy tasks"
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 250, 250, 250)
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }

    fun showLazyReminder(
        context: Context,
        taskTitle: String,
        notificationId: Int = System.currentTimeMillis().toInt()
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("😴 Oi! Still haven't done this?")
            .setContentText(taskTitle)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("$taskTitle\n\nToo lazy? Shake your phone in Aalsi Mode to complete it!")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 250, 250, 250))
            .build()

        val notificationManager = getSystemService(
            context,
            NotificationManager::class.java
        )

        notificationManager?.notify(notificationId, notification)
    }

    fun showDailySummary(
        context: Context,
        completedCount: Int
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val message = when {
            completedCount == 0 -> "You completed 0 tasks today. That's... something? 😴"
            completedCount < 3 -> "You completed $completedCount tasks today (sort of). Keep going! 🐌"
            else -> "Great job! You completed $completedCount tasks today! 🎉"
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.star_on)
            .setContentTitle("🌙 Daily Summary")
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(message)
            )
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService(
            context,
            NotificationManager::class.java
        )

        notificationManager?.notify(2, notification)
    }
}


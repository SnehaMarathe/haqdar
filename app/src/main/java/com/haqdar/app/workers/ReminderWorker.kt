package com.haqdar.app.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.haqdar.app.R

class ReminderWorker(
  appContext: Context,
  params: WorkerParameters
) : CoroutineWorker(appContext, params) {

  override suspend fun doWork(): Result {
    showNotification(
      title = "Haqdar: Quick check",
      body = "Open Haqdar to see if any benefits/actions apply to you today."
    )
    return Result.success()
  }

  private fun showNotification(title: String, body: String) {
    val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "haqdar_reminders"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val ch = NotificationChannel(channelId, "Haqdar Reminders", NotificationManager.IMPORTANCE_DEFAULT)
      nm.createNotificationChannel(ch)
    }

    val n = NotificationCompat.Builder(applicationContext, channelId)
      .setSmallIcon(R.drawable.ic_launcher_foreground)
      .setContentTitle(title)
      .setContentText(body)
      .setAutoCancel(true)
      .build()

    nm.notify(1001, n)
  }
}

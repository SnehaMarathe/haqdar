package com.haqdar.app.workers

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object WorkerScheduler {
  fun scheduleDailyReminders(context: Context) {
    val req = PeriodicWorkRequestBuilder<ReminderWorker>(24, TimeUnit.HOURS).build()
    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
      "haqdar_daily_reminder",
      ExistingPeriodicWorkPolicy.KEEP,
      req
    )
  }
}

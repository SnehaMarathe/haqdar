package com.haqdar.app

import android.app.Application
import com.haqdar.app.data.AppDatabase
import com.haqdar.app.workers.WorkerScheduler

class HaqdarApp : Application() {
  lateinit var db: AppDatabase
    private set

  override fun onCreate() {
    super.onCreate()
    db = AppDatabase.get(this)
    WorkerScheduler.scheduleDailyReminders(this)
  }
}

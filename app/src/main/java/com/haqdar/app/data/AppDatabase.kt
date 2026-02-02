package com.haqdar.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.haqdar.app.data.dao.ProfileDao
import com.haqdar.app.data.dao.RecommendationDao
import com.haqdar.app.data.dao.SchemeDao
import com.haqdar.app.data.entities.ProfileEntity
import com.haqdar.app.data.entities.RecommendationEntity
import com.haqdar.app.data.entities.SchemeEntity

@Database(
  entities = [ProfileEntity::class, SchemeEntity::class, RecommendationEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun profileDao(): ProfileDao
  abstract fun schemeDao(): SchemeDao
  abstract fun recommendationDao(): RecommendationDao

  companion object {
    @Volatile private var INSTANCE: AppDatabase? = null

    fun get(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        INSTANCE ?: Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          "haqdar.db"
        ).build().also { INSTANCE = it }
      }
    }
  }
}

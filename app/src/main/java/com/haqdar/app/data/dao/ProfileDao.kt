package com.haqdar.app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haqdar.app.data.entities.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
  @Query("SELECT * FROM profiles WHERE id = 1")
  fun observeProfile(): Flow<ProfileEntity?>

  @Query("SELECT * FROM profiles WHERE id = 1")
  suspend fun getProfile(): ProfileEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsert(profile: ProfileEntity)
}

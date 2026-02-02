package com.haqdar.app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.haqdar.app.data.entities.RecommendationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecommendationDao {
  @Query("SELECT * FROM recommendations ORDER BY priority ASC, createdAtEpoch DESC")
  fun observeAll(): Flow<List<RecommendationEntity>>

  @Query("DELETE FROM recommendations")
  suspend fun clear()

  @Insert
  suspend fun insertAll(items: List<RecommendationEntity>)
}

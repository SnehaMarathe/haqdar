package com.haqdar.app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haqdar.app.data.entities.SchemeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SchemeDao {
  @Query("SELECT * FROM schemes")
  fun observeSchemes(): Flow<List<SchemeEntity>>

  @Query("SELECT * FROM schemes")
  suspend fun getAll(): List<SchemeEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertAll(items: List<SchemeEntity>)

  @Query("SELECT COUNT(*) FROM schemes")
  suspend fun count(): Int
}

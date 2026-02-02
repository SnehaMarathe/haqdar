package com.haqdar.app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommendations")
data class RecommendationEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val schemeId: String,
  val title: String,
  val whyItApplies: String,
  val priority: Int,
  val createdAtEpoch: Long
)

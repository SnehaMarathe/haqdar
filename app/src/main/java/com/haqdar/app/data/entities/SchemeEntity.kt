package com.haqdar.app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schemes")
data class SchemeEntity(
  @PrimaryKey val schemeId: String,
  val title: String,
  val category: String,
  val descriptionPlain: String,
  val actionText: String,
  val deepLink: String?,
  val ruleKey: String
)

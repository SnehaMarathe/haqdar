package com.haqdar.app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
  @PrimaryKey val id: Long = 1L,
  val name: String,
  val age: Int,
  val state: String,
  val district: String,
  val incomeAnnual: Int,
  val employmentType: String,
  val hasAadhaar: Boolean,
  val hasPan: Boolean,
  val isWoman: Boolean
)

package com.haqdar.app.domain.model

data class Profile(
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

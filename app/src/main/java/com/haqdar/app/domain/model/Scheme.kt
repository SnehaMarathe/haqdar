package com.haqdar.app.domain.model

data class Scheme(
  val schemeId: String,
  val title: String,
  val category: String,
  val descriptionPlain: String,
  val actionText: String,
  val deepLink: String?,
  val ruleKey: String
)

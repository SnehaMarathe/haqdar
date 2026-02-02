package com.haqdar.app.domain.model

data class Recommendation(
  val schemeId: String,
  val title: String,
  val whyItApplies: String,
  val priority: Int
)

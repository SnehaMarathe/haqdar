package com.haqdar.app.domain.rules

import com.haqdar.app.domain.model.Profile
import com.haqdar.app.domain.model.Recommendation
import com.haqdar.app.domain.model.Scheme

class EligibilityEngine {

  fun compute(profile: Profile, schemes: List<Scheme>): List<Recommendation> {
    val recs = mutableListOf<Recommendation>()

    for (s in schemes) {
      when (s.ruleKey) {
        "RULE_TAX_REGIME" -> {
          if (profile.hasPan && profile.incomeAnnual > 250000) {
            recs += Recommendation(
              schemeId = s.schemeId,
              title = s.title,
              whyItApplies = "You earn ₹${profile.incomeAnnual}/year and have PAN. Regime choice can impact your take-home.",
              priority = 1
            )
          }
        }

        "RULE_SENIOR" -> {
          if (profile.age >= 60) {
            recs += Recommendation(
              schemeId = s.schemeId,
              title = s.title,
              whyItApplies = "You are ${profile.age}. Many benefits are age-triggered; this reduces dependency on others.",
              priority = 1
            )
          }
        }

        "RULE_WOMEN" -> {
          if (profile.isWoman) {
            recs += Recommendation(
              schemeId = s.schemeId,
              title = s.title,
              whyItApplies = "Women-specific supports often have separate eligibility tracks; you should not miss them.",
              priority = 2
            )
          }
        }

        "RULE_LOW_INCOME" -> {
          if (profile.incomeAnnual <= 250000 || profile.employmentType in setOf("informal")) {
            recs += Recommendation(
              schemeId = s.schemeId,
              title = s.title,
              whyItApplies = "Your profile suggests you may qualify for multiple entitlements; checking now avoids missed benefits.",
              priority = 1
            )
          }
        }

        "RULE_GIG" -> {
          if (profile.employmentType in setOf("gig", "self")) {
            val why = if (!profile.hasPan) {
              "You are ${profile.employmentType}. Getting PAN is step #1 to avoid future compliance issues."
            } else {
              "You are ${profile.employmentType} with PAN. Simple compliance habits can prevent penalties."
            }
            recs += Recommendation(
              schemeId = s.schemeId,
              title = s.title,
              whyItApplies = why,
              priority = 2
            )
          }
        }
      }
    }

    val likelyMissed = estimateMissedMoney(profile)
    if (likelyMissed >= 5000) {
      recs += Recommendation(
        schemeId = "MISSED_MONEY_METER",
        title = "Missed Money Meter",
        whyItApplies = "Based on your profile, you may be missing ~₹$likelyMissed/year in benefits or savings.",
        priority = 1
      )
    }

    return recs.sortedWith(compareBy({ it.priority }, { it.title }))
  }

  private fun estimateMissedMoney(profile: Profile): Int {
    var score = 0
    if (!profile.hasPan) score += 4000
    if (!profile.hasAadhaar) score += 3000
    if (profile.incomeAnnual <= 250000) score += 6000
    if (profile.employmentType in setOf("gig", "informal")) score += 5000
    if (profile.age >= 60) score += 3000
    if (profile.isWoman) score += 2000
    return score
  }
}

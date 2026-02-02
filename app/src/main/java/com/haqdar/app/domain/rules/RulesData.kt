package com.haqdar.app.domain.rules

import com.haqdar.app.data.entities.SchemeEntity

object RulesData {
  fun seedSchemes(): List<SchemeEntity> = listOf(
    SchemeEntity(
      schemeId = "TAX_NEW_REGIME_HINT",
      title = "Tax Regime Helper (Old vs New)",
      category = "tax",
      descriptionPlain = "Simple guidance to reduce confusion and avoid overpaying.",
      actionText = "Answer 5 questions to see which regime suits you.",
      deepLink = null,
      ruleKey = "RULE_TAX_REGIME"
    ),
    SchemeEntity(
      schemeId = "SENIOR_BENEFITS",
      title = "Senior Citizen Benefits Check",
      category = "senior",
      descriptionPlain = "Age-based benefits and common exemptions simplified.",
      actionText = "Verify your age-based benefits list.",
      deepLink = null,
      ruleKey = "RULE_SENIOR"
    ),
    SchemeEntity(
      schemeId = "WOMEN_SUPPORT",
      title = "Women-Focused Support",
      category = "women",
      descriptionPlain = "Women-targeted support checklist based on your profile and state.",
      actionText = "See what you can claim or apply for.",
      deepLink = null,
      ruleKey = "RULE_WOMEN"
    ),
    SchemeEntity(
      schemeId = "LOW_INCOME_WELFARE",
      title = "Low-Income Welfare Entitlements",
      category = "welfare",
      descriptionPlain = "Find likely entitlements and required documents.",
      actionText = "Check your likely entitlements in 2 minutes.",
      deepLink = null,
      ruleKey = "RULE_LOW_INCOME"
    ),
    SchemeEntity(
      schemeId = "GIG_COMPLIANCE",
      title = "Gig/Self-employed Tax & Compliance Basics",
      category = "employment",
      descriptionPlain = "Avoid surprises: PAN, advance tax, and basic record tips.",
      actionText = "Get your next 30 days action list.",
      deepLink = null,
      ruleKey = "RULE_GIG"
    )
  )
}

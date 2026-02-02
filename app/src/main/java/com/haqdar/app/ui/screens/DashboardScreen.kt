package com.haqdar.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haqdar.app.HaqdarApp
import com.haqdar.app.data.entities.RecommendationEntity
import com.haqdar.app.data.repo.ProfileRepository
import com.haqdar.app.data.repo.RecommendationRepository
import com.haqdar.app.data.repo.SchemeRepository
import com.haqdar.app.domain.model.Profile
import com.haqdar.app.domain.model.Scheme
import com.haqdar.app.domain.rules.EligibilityEngine
import com.haqdar.app.domain.rules.RulesData
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(app: HaqdarApp, onOpenBenefits: () -> Unit) {
  val scope = rememberCoroutineScope()

  val profileRepo = remember { ProfileRepository(app.db.profileDao()) }
  val schemeRepo = remember { SchemeRepository(app.db.schemeDao()) }
  val recRepo = remember { RecommendationRepository(app.db.recommendationDao()) }

  val profile by profileRepo.observe().collectAsState(initial = null)
  val recs by recRepo.observe().collectAsState(initial = emptyList())

  LaunchedEffect(Unit) {
    schemeRepo.ensureSeeded(RulesData.seedSchemes())
    scope.launch { refreshRecommendations(app) }
  }

  Surface(Modifier.fillMaxSize()) {
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Text("My Actions", style = MaterialTheme.typography.headlineSmall)

      if (profile == null) {
        Text("Complete onboarding to get personalized actions.")
      } else {
        Text("Hello, ${profile!!.name}. Here’s what matters for you.")
      }

      if (recs.isEmpty()) {
        Text("No actions yet. Tap refresh.")
      } else {
        recs.take(6).forEach { r ->
          Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(r.title, style = MaterialTheme.typography.titleMedium)
              Text(r.whyItApplies, style = MaterialTheme.typography.bodyMedium)
              Text(
                text = when (r.priority) {
                  1 -> "Do now"
                  2 -> "Do soon"
                  else -> "Keep an eye"
                },
                style = MaterialTheme.typography.labelMedium
              )
            }
          }
        }
      }

      Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
        Button(
          modifier = Modifier.weight(1f),
          onClick = { scope.launch { refreshRecommendations(app) } }
        ) { Text("Refresh") }

        OutlinedButton(
          modifier = Modifier.weight(1f),
          onClick = onOpenBenefits
        ) { Text("My Benefits") }
      }
    }
  }
}

private suspend fun refreshRecommendations(app: HaqdarApp) {
  val profileDao = app.db.profileDao()
  val schemeDao = app.db.schemeDao()
  val recDao = app.db.recommendationDao()

  val p = profileDao.getProfile() ?: return
  val schemeList = schemeDao.getAll().ifEmpty { RulesData.seedSchemes() }.map {
    Scheme(
      schemeId = it.schemeId,
      title = it.title,
      category = it.category,
      descriptionPlain = it.descriptionPlain,
      actionText = it.actionText,
      deepLink = it.deepLink,
      ruleKey = it.ruleKey
    )
  }

  val profile = Profile(
    name = p.name,
    age = p.age,
    state = p.state,
    district = p.district,
    incomeAnnual = p.incomeAnnual,
    employmentType = p.employmentType,
    hasAadhaar = p.hasAadhaar,
    hasPan = p.hasPan,
    isWoman = p.isWoman
  )

  val engine = EligibilityEngine()
  val computed = engine.compute(profile, schemeList)

  val entities = computed.map {
    RecommendationEntity(
      schemeId = it.schemeId,
      title = it.title,
      whyItApplies = it.whyItApplies,
      priority = it.priority,
      createdAtEpoch = System.currentTimeMillis()
    )
  }

  recDao.clear()
  recDao.insertAll(entities)
}

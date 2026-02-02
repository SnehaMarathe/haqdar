package com.haqdar.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haqdar.app.HaqdarApp
import com.haqdar.app.data.repo.SchemeRepository
import com.haqdar.app.domain.rules.RulesData

@Composable
fun BenefitsScreen(app: HaqdarApp) {
  val repo = remember { SchemeRepository(app.db.schemeDao()) }
  val schemes by repo.observe().collectAsState(initial = emptyList())

  LaunchedEffect(Unit) {
    repo.ensureSeeded(RulesData.seedSchemes())
  }

  Surface(Modifier.fillMaxSize()) {
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Text("My Benefits (Catalog)", style = MaterialTheme.typography.headlineSmall)
      Text("In MVP, this is a small local catalog. Later it will sync official updates.", style = MaterialTheme.typography.bodySmall)

      schemes.forEach { s ->
        Card(Modifier.fillMaxWidth()) {
          Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(s.title, style = MaterialTheme.typography.titleMedium)
            Text(s.descriptionPlain, style = MaterialTheme.typography.bodyMedium)
            Text("Action: ${s.actionText}", style = MaterialTheme.typography.labelMedium)
          }
        }
      }
    }
  }
}

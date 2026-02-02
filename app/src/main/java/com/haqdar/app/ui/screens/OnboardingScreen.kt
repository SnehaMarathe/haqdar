package com.haqdar.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.haqdar.app.HaqdarApp
import com.haqdar.app.data.entities.ProfileEntity
import com.haqdar.app.data.repo.ProfileRepository
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(app: HaqdarApp, onDone: () -> Unit) {
  val scope = rememberCoroutineScope()
  val repo = remember { ProfileRepository(app.db.profileDao()) }

  var name by remember { mutableStateOf("") }
  var age by remember { mutableIntStateOf(28) }
  var state by remember { mutableStateOf("Maharashtra") }
  var district by remember { mutableStateOf("Mumbai") }
  var income by remember { mutableIntStateOf(500000) }
  var employmentType by remember { mutableStateOf("salaried") }
  var hasAadhaar by remember { mutableStateOf(true) }
  var hasPan by remember { mutableStateOf(true) }
  var isWoman by remember { mutableStateOf(false) }

  Surface(Modifier.fillMaxSize()) {
    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Text(text = "Haqdar", style = MaterialTheme.typography.headlineMedium)
      Text(text = "We’ll personalize benefits and actions for you.", style = MaterialTheme.typography.bodyMedium)

      OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())

      Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
          value = age.toString(),
          onValueChange = { it.toIntOrNull()?.let { v -> age = v.coerceIn(13, 100) } },
          label = { Text("Age") },
          modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
          value = income.toString(),
          onValueChange = { it.toIntOrNull()?.let { v -> income = v.coerceAtLeast(0) } },
          label = { Text("Annual Income (₹)") },
          modifier = Modifier.weight(2f)
        )
      }

      OutlinedTextField(value = state, onValueChange = { state = it }, label = { Text("State") }, modifier = Modifier.fillMaxWidth())
      OutlinedTextField(value = district, onValueChange = { district = it }, label = { Text("District/City") }, modifier = Modifier.fillMaxWidth())

      EmploymentTypePicker(value = employmentType, onChange = { employmentType = it })

      Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        FilterChip(selected = hasAadhaar, onClick = { hasAadhaar = !hasAadhaar }, label = { Text("Aadhaar") })
        FilterChip(selected = hasPan, onClick = { hasPan = !hasPan }, label = { Text("PAN") })
        FilterChip(selected = isWoman, onClick = { isWoman = !isWoman }, label = { Text("Woman") })
      }

      Spacer(Modifier.height(8.dp))

      Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
          scope.launch {
            val p = ProfileEntity(
              id = 1L,
              name = if (name.isBlank()) "User" else name.trim(),
              age = age,
              state = state.trim(),
              district = district.trim(),
              incomeAnnual = income,
              employmentType = employmentType,
              hasAadhaar = hasAadhaar,
              hasPan = hasPan,
              isWoman = isWoman
            )
            repo.upsert(p)
            onDone()
          }
        }
      ) { Text("Continue") }

      Text(
        text = "Privacy: works offline. Linking IDs is optional in future versions.",
        style = MaterialTheme.typography.bodySmall
      )
    }
  }
}

@Composable
private fun EmploymentTypePicker(value: String, onChange: (String) -> Unit) {
  val options = listOf("salaried", "self", "gig", "informal", "student", "senior")
  Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
    Text("Employment Type", style = MaterialTheme.typography.labelLarge)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      options.take(3).forEach { opt ->
        AssistChip(onClick = { onChange(opt) }, label = { Text(opt) })
      }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      options.drop(3).forEach { opt ->
        AssistChip(onClick = { onChange(opt) }, label = { Text(opt) })
      }
    }
    Text("Selected: $value", style = MaterialTheme.typography.bodySmall)
  }
}

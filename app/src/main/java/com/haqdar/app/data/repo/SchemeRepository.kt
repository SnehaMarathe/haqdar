package com.haqdar.app.data.repo

import com.haqdar.app.data.dao.SchemeDao
import com.haqdar.app.data.entities.SchemeEntity
import kotlinx.coroutines.flow.Flow

class SchemeRepository(private val dao: SchemeDao) {
  fun observe(): Flow<List<SchemeEntity>> = dao.observeSchemes()
  suspend fun getAll(): List<SchemeEntity> = dao.getAll()

  suspend fun ensureSeeded(seed: List<SchemeEntity>) {
    if (dao.count() == 0) dao.upsertAll(seed)
  }
}

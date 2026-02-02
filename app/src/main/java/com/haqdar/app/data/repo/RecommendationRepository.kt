package com.haqdar.app.data.repo

import com.haqdar.app.data.dao.RecommendationDao
import com.haqdar.app.data.entities.RecommendationEntity
import kotlinx.coroutines.flow.Flow

class RecommendationRepository(private val dao: RecommendationDao) {
  fun observe(): Flow<List<RecommendationEntity>> = dao.observeAll()
  suspend fun replaceAll(items: List<RecommendationEntity>) {
    dao.clear()
    dao.insertAll(items)
  }
}

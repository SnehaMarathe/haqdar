package com.haqdar.app.data.repo

import com.haqdar.app.data.dao.ProfileDao
import com.haqdar.app.data.entities.ProfileEntity
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val dao: ProfileDao) {
  fun observe(): Flow<ProfileEntity?> = dao.observeProfile()
  suspend fun get(): ProfileEntity? = dao.getProfile()
  suspend fun upsert(p: ProfileEntity) = dao.upsert(p)
}

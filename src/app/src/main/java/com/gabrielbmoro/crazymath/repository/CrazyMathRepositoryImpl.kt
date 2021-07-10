package com.gabrielbmoro.crazymath.repository

import com.gabrielbmoro.crazymath.repository.assets.AssetsDataSourceRepositoryImpl
import com.gabrielbmoro.crazymath.domain.model.CrossNumberConfig
import com.gabrielbmoro.crazymath.domain.model.PreviewConfig
import javax.inject.Inject

open class CrazyMathRepositoryImpl @Inject constructor(
        private val firebaseFireStoreRepositoryImpl: FirebaseFireStoreImpl,
        private val localDataSourceRepositoryImpl: LocalDataSourceRepositoryImpl,
        private val firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl,
        private val assetsDataSourceRepositoryImpl: AssetsDataSourceRepositoryImpl
) : CrazyMathRepository {

    override suspend fun signIn(email: String): String? {
        return firebaseAuthRepositoryImpl.signIn(email)
    }

    override suspend fun signUp(email: String): String? {
        return firebaseAuthRepositoryImpl.signUp(email)
    }

    override fun saveToken(token: String): Boolean {
        return localDataSourceRepositoryImpl.saveToken(token)
    }

    override suspend fun getPoints(): List<Long> {
        return firebaseFireStoreRepositoryImpl.getPoints()
    }

    override fun getToken(): String? {
        return localDataSourceRepositoryImpl.getToken()
    }

    override suspend fun sendUsersFeedback(userMessage: String): Boolean {
        return firebaseFireStoreRepositoryImpl.sendUserFeedback(userMessage)
    }

    override fun logout(): Boolean {
        return localDataSourceRepositoryImpl.logout()
    }

    override fun sawTheWelcomeScreen() {
        localDataSourceRepositoryImpl.saveThatUserSeeWelcomeScreen()
    }

    override fun hasUserSeenTheWelcomeScreenUseCase(): Boolean {
        return localDataSourceRepositoryImpl.getHasTheUserSeenTheWelcomeScreen()
    }

    override suspend fun sendCrossNumberResult(firebaseUID: String, timestamp: Long, points: Long): Boolean {
        return firebaseFireStoreRepositoryImpl.sendCrossNumberResult(
                firebaseUID = firebaseUID,
                timestamp = timestamp,
                points = points
        )
    }

    override fun getUserId(): String? {
        return firebaseAuthRepositoryImpl.userID()
    }

    override fun getCrossNumberConfigurations(): List<CrossNumberConfig> {
        return assetsDataSourceRepositoryImpl.getCrossNumberConfigurations()
    }

    override fun getPreview(): PreviewConfig {
        return assetsDataSourceRepositoryImpl.getPreviewConfiguration()
    }
}
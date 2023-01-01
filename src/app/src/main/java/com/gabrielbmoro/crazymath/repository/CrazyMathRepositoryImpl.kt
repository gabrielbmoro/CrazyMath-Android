package com.gabrielbmoro.crazymath.repository

import com.gabrielbmoro.crazymath.repository.assets.AssetsDataSourceRepositoryImpl
import com.gabrielbmoro.crazymath.domain.model.CrossNumberConfig
import com.gabrielbmoro.crazymath.domain.model.PreviewConfig
import javax.inject.Inject

open class CrazyMathRepositoryImpl @Inject constructor(
        private val localDataSourceRepositoryImpl: LocalDataSourceRepositoryImpl,
        private val assetsDataSourceRepositoryImpl: AssetsDataSourceRepositoryImpl
) : CrazyMathRepository {

    override suspend fun signIn(email: String): String? {
        // TODO: Fake returned value just to be able to run the app
        return "Fake User ID"
    }

    override suspend fun signUp(email: String): String? {
        // TODO: Fake returned value just to be able to run the app
        return "Fake User ID"
    }

    override fun saveToken(token: String): Boolean {
        return localDataSourceRepositoryImpl.saveToken(token)
    }

    override suspend fun getPoints(): List<Long> {
        // TODO: Fake returned value just to be able to run the app
        return listOf(100L, 121L)
    }

    override fun getToken(): String? {
        return localDataSourceRepositoryImpl.getToken()
    }

    override suspend fun sendUsersFeedback(userMessage: String): Boolean {
        // TODO: Fake returned value just to be able to run the app
        return true
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
        // TODO: Fake returned value just to be able to run the app
        return true
    }

    override fun getUserId(): String? {
        // TODO: Fake returned value just to be able to run the app
        return "Fake User Id"
    }

    override fun getCrossNumberConfigurations(): List<CrossNumberConfig> {
        return assetsDataSourceRepositoryImpl.getCrossNumberConfigurations()
    }

    override fun getPreview(): PreviewConfig {
        return assetsDataSourceRepositoryImpl.getPreviewConfiguration()
    }
}
package com.gabrielbmoro.crazymath.repository

import com.gabrielbmoro.crazymath.domain.model.CrossNumberConfig
import com.gabrielbmoro.crazymath.domain.model.PreviewConfig

interface CrazyMathRepository {

    suspend fun signIn(email: String): String?

    suspend fun signUp(email: String): String?

    suspend fun sendCrossNumberResult(firebaseUID: String, timestamp: Long, points: Long): Boolean

    suspend fun getPoints(): List<Long>

    suspend fun sendUsersFeedback(userMessage: String): Boolean

    fun saveToken(token: String): Boolean

    fun getToken(): String?

    fun logout(): Boolean

    fun sawTheWelcomeScreen()

    fun hasUserSeenTheWelcomeScreenUseCase(): Boolean

    fun getUserId(): String?

    fun getCrossNumberConfigurations(): List<CrossNumberConfig>

    fun getPreview(): PreviewConfig
}
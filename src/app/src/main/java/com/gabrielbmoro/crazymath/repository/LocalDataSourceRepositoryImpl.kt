package com.gabrielbmoro.crazymath.repository

import android.content.SharedPreferences
import javax.inject.Inject

open class LocalDataSourceRepositoryImpl @Inject constructor(
        private val sharedPreferences: SharedPreferences
) {
    fun saveToken(token: String): Boolean {
        sharedPreferences
                .edit()
                .putString(KEY_USER_TOKEN, token)
                .apply()
        return true
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_USER_TOKEN, TOKEN_DEFAULT_VALUE)
    }

    fun logout(): Boolean {
        sharedPreferences.edit()
                .putString(KEY_USER_TOKEN, TOKEN_DEFAULT_VALUE)
                .apply()
        return true
    }

    fun saveThatUserSeeWelcomeScreen() {
        sharedPreferences.edit()
                .putBoolean(KEY_USER_SAW_THE_WELCOME_SCREEN, true)
                .apply()
    }

    fun getHasTheUserSeenTheWelcomeScreen() : Boolean {
        return sharedPreferences.getBoolean(
                KEY_USER_SAW_THE_WELCOME_SCREEN,
                USER_SAW_THE_WELCOME_SCREEN_DEFAULT_VALUE
        )
    }

    companion object {
        const val KEY_USER_SAW_THE_WELCOME_SCREEN = "user_saw_welcome_screen"
        const val USER_SAW_THE_WELCOME_SCREEN_DEFAULT_VALUE = false
        const val KEY_USER_TOKEN = "user_token"
        const val TOKEN_DEFAULT_VALUE = ""
    }

}
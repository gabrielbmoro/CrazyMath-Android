package com.gabrielbmoro.crazymath.repository

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseAuthRepositoryImpl {

    suspend fun signIn(email: String): String? {
        val task = Firebase.auth.signInWithEmailAndPassword(email, BASE_PASSWORD)
        return withContext(Dispatchers.IO) {
            try {
                Tasks.await(task).user?.uid
            } catch (exception: Exception) {
                null
            }
        }
    }

    suspend fun signUp(email: String): String? {
        val task = Firebase.auth.createUserWithEmailAndPassword(email, BASE_PASSWORD)
        return withContext(Dispatchers.IO) {
            Tasks.await(task).user?.uid
        }
    }

    fun userID(): String? {
        return Firebase.auth.currentUser?.uid
    }

    companion object {
        private const val BASE_PASSWORD = "12345678"
    }

}
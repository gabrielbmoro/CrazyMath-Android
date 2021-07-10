package com.gabrielbmoro.crazymath.repository

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class FirebaseFireStoreImpl {

    suspend fun sendCrossNumberResult(firebaseUID: String, timestamp: Long, points: Long): Boolean {
        val userRankEntry = HashMap<String, Any>()
        userRankEntry[CROSS_NUMBER_GAME_RANK_USER_ID_KEY] = firebaseUID
        userRankEntry[CROSS_NUMBER_GAME_TIME_STAMP_KEY] = timestamp
        userRankEntry[CROSS_NUMBER_GAME_POINTS_KEY] = points

        val task = FirebaseFirestore.getInstance().collection(CROSS_NUMBER_GAME_RANK_COLLECTION)
                .document().parent
                .add(userRankEntry)

        return withContext(Dispatchers.IO) {
            try {
                Tasks.await(task)
                true
            } catch (exception: Exception) {
                false
            }
        }
    }

    suspend fun getPoints(): List<Long> {
        val task = FirebaseFirestore.getInstance().collection(CROSS_NUMBER_GAME_RANK_COLLECTION)
                .document().parent
                .get()

        return withContext(Dispatchers.IO) {
            try {
                Tasks.await(task)
                        .documents
                        .mapNotNull { documentSnapshot ->
                            documentSnapshot.getLong(
                                    CROSS_NUMBER_GAME_POINTS_KEY
                            )
                        }.toList()
            } catch (exception: Exception) {
                emptyList()
            }
        }
    }

    suspend fun sendUserFeedback(userFeedbackMessage: String): Boolean {
        val feedbackEntry = HashMap<String, Any>()
        feedbackEntry[USER_FEEDBACK_MESSAGE_KEY] = userFeedbackMessage

        val task = FirebaseFirestore.getInstance().collection(USER_FEEDBACK_COLLECTION)
                .document()
                .parent
                .add(feedbackEntry)

        return withContext(Dispatchers.IO) {
            try {
                Tasks.await(task)
                true
            } catch (exception: Exception) {
                false
            }
        }
    }


    companion object {
        const val CROSS_NUMBER_GAME_RANK_COLLECTION = "crossNumberGame"
        const val CROSS_NUMBER_GAME_RANK_USER_ID_KEY = "userId"
        const val CROSS_NUMBER_GAME_TIME_STAMP_KEY = "timestamp"
        const val CROSS_NUMBER_GAME_POINTS_KEY = "points"
        const val USER_FEEDBACK_COLLECTION = "usersFeedback"
        const val USER_FEEDBACK_MESSAGE_KEY = "feedback"
    }
}
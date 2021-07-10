package com.gabrielbmoro.crazymath.helpers.navigation

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.core.Event

open class NavigationController {

    val navigationSubject = MutableLiveData<Event<Pair<NavigationEvent, Bundle?>>>()

    fun dispatchEvent(navigationEvent: NavigationEvent, bundle: Bundle? = null) {
        navigationSubject.postValue(Event(Pair(navigationEvent, bundle)))
    }

    fun listening(lifecycleOwner: LifecycleOwner, navController: NavController) {
        navigationSubject.observe(lifecycleOwner, { evt ->
            evt.getContentIfNotHandled()?.let { event ->
                when (event.first) {
                    NavigationEvent.GO_TO_CROSS_NUMBER_GAME -> navController.navigate(R.id.crossNumberFragment, event.second)
                    NavigationEvent.GO_TO_HOME_SCREEN -> navController.navigate(R.id.goToHomeFragment, event.second)
                    NavigationEvent.GO_TO_SIGN_IN -> navController.navigate(R.id.goToSignInFragment, event.second)
                    NavigationEvent.GO_TO_USER_FEEDBACK -> navController.navigate(R.id.goToUserFeedbackFragment, event.second)
                    NavigationEvent.GO_TO_RANK_RESULT -> navController.navigate(R.id.goToResultFragment, event.second)
                }
            }
        })
    }
}
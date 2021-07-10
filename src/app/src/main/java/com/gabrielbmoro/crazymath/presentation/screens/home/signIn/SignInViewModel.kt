package com.gabrielbmoro.crazymath.presentation.screens.home.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielbmoro.crazymath.core.Event
import com.gabrielbmoro.crazymath.core.extensions.async
import com.gabrielbmoro.crazymath.core.extensions.isEmailValid
import com.gabrielbmoro.crazymath.domain.usecase.SaveTokenUseCase
import com.gabrielbmoro.crazymath.domain.usecase.SignUpUseCase
import com.gabrielbmoro.crazymath.domain.usecase.SignInUseCase
import timber.log.Timber

class SignInViewModel constructor(
        private val saveTokenUseCase: SaveTokenUseCase,
        private val signUpUseCase: SignUpUseCase,
        private val loginUseCase: SignInUseCase
) : ViewModel() {

    private val _liveDataOnSuccessEvent = MutableLiveData<Boolean>()
    val liveDataOnSuccessEvent: LiveData<Boolean> = _liveDataOnSuccessEvent

    private val _liveDataLoading = MutableLiveData(Event(false))
    val liveDataLoading: LiveData<Event<Boolean>> = _liveDataLoading

    private var email: String = ""

    fun signIn(email: String) {
        this.email = email

        if (this.email.isEmailValid()) {
            async(
                    liveDataLoadEvent = _liveDataLoading,
                    callback = {
                        var token = loginUseCase.execute(this@SignInViewModel.email)
                        if (token == null) {
                            token = signUpUseCase.execute(this@SignInViewModel.email)
                        }
                        token?.let {
                            val result = saveTokenUseCase.execute(token)
                            _liveDataOnSuccessEvent.postValue(result)
                        }
                    },
                    errorCallback = {
                        Timber.e(it)
                    }
            )
        }
    }
}
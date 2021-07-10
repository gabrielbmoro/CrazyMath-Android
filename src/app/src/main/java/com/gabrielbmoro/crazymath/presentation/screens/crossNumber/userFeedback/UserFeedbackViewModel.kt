package com.gabrielbmoro.crazymath.presentation.screens.crossNumber.userFeedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielbmoro.crazymath.domain.usecase.SendUserFeedbackUseCase
import com.gabrielbmoro.crazymath.core.Event
import com.gabrielbmoro.crazymath.core.extensions.async
import kotlinx.coroutines.launch
import timber.log.Timber

class UserFeedbackViewModel constructor(
        private val sendUserFeedbackUseCase: SendUserFeedbackUseCase
) : ViewModel() {

    private val _liveDataLoading = MutableLiveData(Event(false))
    val liveDataLoading: LiveData<Event<Boolean>> = _liveDataLoading

    private val _sendUserFeedbackResult = MutableLiveData<Event<Boolean>>()
    val sendUserFeedbackResult: LiveData<Event<Boolean>> = _sendUserFeedbackResult

    fun onSendUserFeedback(message: String) {
        async(
                liveDataLoadEvent = _liveDataLoading,
                callback = {
                    val result = sendUserFeedbackUseCase.execute(message)
                    _sendUserFeedbackResult.value = Event(result)
                },
                errorCallback = {
                    Timber.e(it)
                }
        )
    }
}
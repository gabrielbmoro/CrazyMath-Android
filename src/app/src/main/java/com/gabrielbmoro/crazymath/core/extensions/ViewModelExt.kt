package com.gabrielbmoro.crazymath.core.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielbmoro.crazymath.core.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ViewModel.async(liveDataLoadEvent: MutableLiveData<Event<Boolean>>, callback: suspend CoroutineScope.() -> Unit, errorCallback: (Throwable) -> Unit) {
    liveDataLoadEvent.value = Event(true)
    viewModelScope.launch {
        try {
            callback.invoke(this)
        } catch (throwable: Throwable) {
            errorCallback.invoke(throwable)
        } finally {
            liveDataLoadEvent.value = Event(false)
        }
    }
}
package com.gabrielbmoro.crazymath.presentation.screens.crossNumber.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielbmoro.crazymath.domain.usecase.GetRankPositionUseCase
import com.gabrielbmoro.crazymath.core.Event
import com.gabrielbmoro.crazymath.core.extensions.async
import timber.log.Timber

class ResultViewModel constructor(
        val points: Long,
        wasSendToRank: Boolean,
        getRankPosition: GetRankPositionUseCase
) : ViewModel() {

    private val _rankPosition = MutableLiveData<Event<Int>>()
    val rankPosition: LiveData<Event<Int>> = _rankPosition

    private val _liveDataLoading = MutableLiveData<Event<Boolean>>()
    val liveDataLoading: LiveData<Event<Boolean>> = _liveDataLoading

    init {
        if (wasSendToRank) {
            async(
                    callback = {
                        val rankPosition = getRankPosition.execute(points)
                        if (rankPosition > 0) {
                            _rankPosition.value = Event(rankPosition)
                        }
                    },
                    liveDataLoadEvent = _liveDataLoading,
                    errorCallback = {
                        Timber.e(it)
                    }
            )
        }
    }
}
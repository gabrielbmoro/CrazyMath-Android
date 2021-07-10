package com.gabrielbmoro.crazymath.presentation.screens.crossNumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielbmoro.crazymath.core.Event
import com.gabrielbmoro.crazymath.core.extensions.async
import com.gabrielbmoro.crazymath.domain.model.UserLevel
import com.gabrielbmoro.crazymath.domain.usecase.GetCrossNumberUseCase
import com.gabrielbmoro.crazymath.domain.usecase.SendCrossNumberGameResultUseCase
import com.gabrielbmoro.crazymath.helpers.navigation.NavigationController
import com.gabrielbmoro.crazymath.helpers.navigation.NavigationEvent
import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.GridCell
import com.gabrielbmoro.crazymath.presentation.extensions.convertRowsToGridCells
import com.gabrielbmoro.crazymath.presentation.screens.crossNumber.result.ResultFragmentArgs
import timber.log.Timber

class CrossNumberViewModel constructor(
        private val userLevel: UserLevel,
        private val navigationController: NavigationController,
        private val crossNumberUseCase: GetCrossNumberUseCase,
        private val sendResultUseCase: SendCrossNumberGameResultUseCase,
) : ViewModel() {

    private val _rows = ArrayList<GridCell>()
    val rows: List<GridCell> = _rows

    private val _liveDataLoading = MutableLiveData(Event(false))
    val liveDataLoading: LiveData<Event<Boolean>> = _liveDataLoading

    private var timeInSeconds: Long = DEFAULT_TIME_SECONDS

    private var hits = 0

    init {
        refresh()
    }

    fun dimen() = 6

    fun refresh() {
        reset()
        val elements = crossNumberUseCase.execute(userLevel)
        _rows.clear()
        _rows.addAll(elements.convertRowsToGridCells())
    }

    private fun reset() {
        timeInSeconds = DEFAULT_TIME_SECONDS
        hits = 0
    }

    fun onSelectTheEquation(isEquationRight: Boolean, currentTimeInSeconds: Long) {
        if (isEquationRight) {
            hits += 10
        }
        timeInSeconds = currentTimeInSeconds
    }

    fun onFinishTheGame() {
        val points = totalPoints()
        async(
                liveDataLoadEvent = _liveDataLoading,
                callback = {
                    val wasSuccess = sendResultUseCase.execute(points = points)
                    navigationController.dispatchEvent(
                            NavigationEvent.GO_TO_RANK_RESULT,
                            ResultFragmentArgs(
                                    points,
                                    wasSuccess
                            ).toBundle()
                    )
                },
                errorCallback = {
                    Timber.e(it)
                }
        )
    }

    private fun totalPoints(): Long = hits * timeInSeconds

    fun goToUserFeedback() {
        navigationController.dispatchEvent(NavigationEvent.GO_TO_USER_FEEDBACK)
    }

    companion object {
        private const val DEFAULT_TIME_SECONDS = 30L
    }
}
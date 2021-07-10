package com.gabrielbmoro.crazymath.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielbmoro.crazymath.helpers.navigation.NavigationController
import com.gabrielbmoro.crazymath.helpers.navigation.NavigationEvent
import com.gabrielbmoro.crazymath.domain.model.GridPreview
import com.gabrielbmoro.crazymath.domain.model.UserLevel
import com.gabrielbmoro.crazymath.domain.usecase.GetPreviewUseCase
import com.gabrielbmoro.crazymath.domain.usecase.GetTokenUseCase
import com.gabrielbmoro.crazymath.core.Event
import com.gabrielbmoro.crazymath.presentation.components.grid.GridViewComponent
import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.GridCell
import com.gabrielbmoro.crazymath.presentation.components.levelSlider.LevelSliderComponent
import com.gabrielbmoro.crazymath.presentation.components.levelSlider.LevelValue
import com.gabrielbmoro.crazymath.presentation.extensions.convertRowsToGridCells
import com.gabrielbmoro.crazymath.presentation.screens.crossNumber.CrossNumberFragmentArgs

class HomeViewModel constructor(
        private val getPreviewUseCase: GetPreviewUseCase,
        private val getTokenUseCase: GetTokenUseCase,
        private val navigator: NavigationController
) : ViewModel() {

    val showErrorMessage = MutableLiveData<String>()

    var orientationCode: Int? = null
        private set

    var exampleFirstEquationIndex: Int? = null
        private set

    var userLevel: UserLevel? = null
        private set

    private val _previewMutable = MutableLiveData<Event<List<GridCell>>>()
    val preview: LiveData<Event<List<GridCell>>> = _previewMutable

    fun login() {
        getTokenUseCase.execute()?.let { token ->
            if (token.isNotEmpty()) {
                goToCrossNumberGame()
            }
        }
    }

    fun onLevelChange(level: LevelValue) {
        userLevel = when (level.levelValue) {
            LevelSliderComponent.EASY_LEVEL_VALUE -> UserLevel.EASY
            LevelSliderComponent.MEDIUM_LEVEL_VALUE -> UserLevel.MEDIUM
            LevelSliderComponent.HARD_LEVEL_VALUE -> UserLevel.HARD
            else -> null
        }
        userLevel?.let {
            updatePreview(it)
        }
    }

    private fun updatePreview(userLevel: UserLevel) {
        val preview = getPreviewUseCase.execute(userLevel)
        exampleFirstEquationIndex = preview.exampleFirstEquationIndex
        orientationCode = mapOrientationToOrientationCode(preview.orientation())
        _previewMutable.value = Event(preview.rows.convertRowsToGridCells())
    }

    private fun mapOrientationToOrientationCode(orientation: GridPreview.Orientation?) =
            when (orientation) {
                GridPreview.Orientation.VERTICAL_INVERSE -> GridViewComponent.ORIENTATION_VERTICAL_INVERSE
                GridPreview.Orientation.VERTICAL -> GridViewComponent.ORIENTATION_VERTICAL
                GridPreview.Orientation.HORIZONTAL -> GridViewComponent.ORIENTATION_HORIZONTAL
                else -> null
            }

    fun goToCrossNumberGame() {
        userLevel?.let { level ->
            navigator.dispatchEvent(
                    NavigationEvent.GO_TO_CROSS_NUMBER_GAME,
                    CrossNumberFragmentArgs(level.value).toBundle()
            )
        }
    }

    fun goToSignIn() {
        navigator.dispatchEvent(
                NavigationEvent.GO_TO_SIGN_IN
        )
    }
}
package com.gabrielbmoro.crazymath.core

import android.content.Context
import com.gabrielbmoro.crazymath.helpers.navigation.NavigationController
import com.gabrielbmoro.crazymath.repository.*
import com.gabrielbmoro.crazymath.repository.assets.AssetsDataSourceRepositoryImpl
import com.gabrielbmoro.crazymath.domain.model.UserLevel
import com.gabrielbmoro.crazymath.domain.usecase.*
import com.gabrielbmoro.crazymath.helpers.MediaPlayerHandler
import com.gabrielbmoro.crazymath.helpers.VibrationHandler
import com.gabrielbmoro.crazymath.presentation.screens.crossNumber.CrossNumberViewModel
import com.gabrielbmoro.crazymath.presentation.screens.crossNumber.result.ResultViewModel
import com.gabrielbmoro.crazymath.presentation.screens.crossNumber.userFeedback.UserFeedbackViewModel
import com.gabrielbmoro.crazymath.presentation.screens.home.HomeViewModel
import com.gabrielbmoro.crazymath.presentation.screens.home.signIn.SignInViewModel
import com.gabrielbmoro.crazymath.presentation.screens.splash.SplashViewModel
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single {
        CrazyMathRepositoryImpl(
                localDataSourceRepositoryImpl = LocalDataSourceRepositoryImpl(
                        androidContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                ),
                assetsDataSourceRepositoryImpl = AssetsDataSourceRepositoryImpl(
                        androidContext().resources,
                        Gson()
                )
        )
    } bind CrazyMathRepository::class
}

val helpersModule = module {
    single {
        NavigationController()
    }
    factory {
        MediaPlayerHandler()
    }
    factory {
        VibrationHandler()
    }
}

val useCaseModule = module {
    single {
        GetCrossNumberUseCase(get())
    }
    single {
        GetTokenUseCase(get())
    }
    single {
        HasUserSeenTheWelcomeScreenUseCase(get())
    }
    single {
        SaveTokenUseCase(get())
    }
    single {
        SendCrossNumberGameResultUseCase(get())
    }
    single {
        SignInUseCase(get())
    }
    single {
        SignUpUseCase(get())
    }
    single {
        UserSawTheWelcomeScreenUseCase(get())
    }
    single {
        GetPreviewUseCase(get())
    }
    single {
        GetRankPositionUseCase(get())
    }
    single {
        SendUserFeedbackUseCase(get())
    }
}

val viewModelModule = module {
    viewModel {
        SplashViewModel(
                hasUserSeenTheWelcomeScreenUseCase = get(),
                navigator = get(),
                userSawTheWelcomeScreenUseCase = get()
        )
    }
    viewModel {
        HomeViewModel(get(), get(), get())
    }
    viewModel {
        SignInViewModel(get(), get(), get())
    }
    viewModel { (level: UserLevel) ->
        CrossNumberViewModel(level, get(), get(), get())
    }
    viewModel { (points: Long, wasSendToRank: Boolean) ->
        ResultViewModel(points, wasSendToRank, get())
    }
    viewModel {
        UserFeedbackViewModel(get())
    }
}

private const val PREFERENCES_NAME = "crazy-math-preferences"
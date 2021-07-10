package com.gabrielbmoro.crazymath.presentation.screens.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.databinding.FragmentSplashBinding
import com.gabrielbmoro.crazymath.presentation.extensions.showFor
import com.gabrielbmoro.crazymath.presentation.extensions.typeTheText

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModel()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        if (viewModel.shouldUserSeeTheMessageOrNotAndSetThatHeSaw()) {

            binding.fragmentSplashHeaderImage.showFor(SHOW_THE_NAVOS_IMAGE_DURATION) {

                binding.fragmentSplashWelcomeMessage.typeTheText(
                        fullText = resources.getString(R.string.splash_screen_welcome_message),
                        animationDuration = TYPE_TEXT_DURATION
                ) {
                    binding.fragmentSplashAuxiliaryButton.isVisible = true

                    Handler(Looper.getMainLooper()).postDelayed({
                        viewModel.goToHomeScreen()
                    }, DURATION_TO_CALL_ANOTHER_SCREEN_AFTER_ANIMATION)
                }
            }
        }

        return binding.root
    }

    companion object {
        private const val SHOW_THE_NAVOS_IMAGE_DURATION = 3000L
        private const val TYPE_TEXT_DURATION = 12000L
        private const val DURATION_TO_CALL_ANOTHER_SCREEN_AFTER_ANIMATION = 1000L
    }
}
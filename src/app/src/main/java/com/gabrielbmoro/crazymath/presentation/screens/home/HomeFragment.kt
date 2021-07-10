package com.gabrielbmoro.crazymath.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.databinding.FragmentHomeBinding
import com.gabrielbmoro.crazymath.presentation.components.ClickableView
import com.gabrielbmoro.crazymath.presentation.components.levelSlider.LevelValue

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navoTalk.setup(
                text = resources.getString(R.string.login_disclaimer),
                links = listOf(getHereLinkEvent())
        )

        binding.fragmentLoginPlayCrossNumber.setOnClickListener {
            viewModel.goToCrossNumberGame()
        }

        viewModel.showErrorMessage.observe(viewLifecycleOwner, { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        viewModel.preview.observe(viewLifecycleOwner, { previewEvent ->
            val startIndex = viewModel.exampleFirstEquationIndex
            val orientationCode = viewModel.orientationCode
            val preview = previewEvent.peekContent()

            if (startIndex != null && orientationCode != null) {
                binding.preview.start(
                        previewElements = preview,
                        startIndex = startIndex,
                        orientationCode = orientationCode
                )
            }
        })

        changeLevelVisualState(binding.sliderLevel.currentLevel)

        binding.sliderLevel.setListener { selectedLevel ->
            changeLevelVisualState(selectedLevel)
        }
    }

    private fun changeLevelVisualState(levelValue: LevelValue) {
        binding.tvHomeLevel.setText(levelValue.levelDescription)

        viewModel.onLevelChange(levelValue)
    }

    private fun getHereLinkEvent(): Pair<String, ClickableView> {
        return Pair(getString(R.string.click_here), object : ClickableView {
            override fun invoke() {
               viewModel.goToSignIn()
            }
        })
    }
}
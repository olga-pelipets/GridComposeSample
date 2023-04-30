package com.example.common_ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.fragments.BaseFragment
import com.example.components.theme.GridTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SummaryFragment : BaseFragment() {
    private val viewModel by viewModels<SummaryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycle.addObserver(viewModel)
        startCoroutineScope()
        uiScope.launch {
            viewModel.events.collect { handleEvent(it) }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                GridTheme {
                    Surface {
                        SummaryScreen(viewModel)
                    }
                }
            }
        }
    }

    private fun handleEvent(event: SummaryViewModel.Event) {
        when (event) {
            SummaryViewModel.Event.WeatherAppEvent -> {
                findNavController().navigate(
                    SummaryFragmentDirections.navigateToWeatherApp()
                )
            }
            SummaryViewModel.Event.ButtonsEvent -> {
                findNavController().navigate(
                    SummaryFragmentDirections.navigateToButtonsScreen()
                )
            }
            SummaryViewModel.Event.TypographyEvent -> {
                findNavController().navigate(
                    SummaryFragmentDirections.navigateToTypographyScreen()
                )
            }
            SummaryViewModel.Event.TextEvent -> {
                Toast.makeText(requireContext(), "Test event", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

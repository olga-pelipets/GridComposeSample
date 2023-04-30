package com.example.info_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.base.fragments.BaseFragment
import com.example.components.theme.GridTheme
import com.example.info_ui.ui.ContentView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdditionalInfoScreenFragment : BaseFragment() {

    private val viewModel by viewModels<AdditionalInfoScreenViewModel>()
    private val args: AdditionalInfoScreenFragmentArgs by navArgs()
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
                        ContentView(viewModel = viewModel, args = args)
                    }
                }
            }
        }
    }

    private fun handleEvent(event: AdditionalInfoScreenViewModel.Event) {
        when (event) {
            AdditionalInfoScreenViewModel.Event.OnBackPressed -> {
                findNavController().popBackStack()
            }
        }
    }
}

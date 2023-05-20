package com.example.common_ui.buttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.base.fragments.BaseFragment
import com.example.components.theme.GridTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ButtonsFragment : BaseFragment() {
    private val viewModel by viewModels<ButtonsViewModel>()

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
                        ButtonsScreen(viewModel)
                    }
                }
            }
        }
    }

    private fun handleEvent(event: ButtonsViewModel.Event) {
        when (event) {
            ButtonsViewModel.Event.PrimaryButtonClick -> {
                Toast.makeText(requireContext(), "PrimaryButtonClick", Toast.LENGTH_SHORT).show()
            }
            ButtonsViewModel.Event.SecondaryButtonClick -> {
                Toast.makeText(requireContext(), "SecondaryButtonClick", Toast.LENGTH_SHORT).show()
            }
            ButtonsViewModel.Event.TextButtonClick -> {
                Toast.makeText(requireContext(), "TextButtonClick", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

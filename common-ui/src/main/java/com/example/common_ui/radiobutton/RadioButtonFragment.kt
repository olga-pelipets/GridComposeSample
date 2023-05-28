package com.example.common_ui.radiobutton

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
class RadioButtonFragment : BaseFragment() {
    private val viewModel by viewModels<RadioButtonViewModel>()

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
                        RadioButtonScreen(viewModel)
                    }
                }
            }
        }
    }

    private fun handleEvent(event: RadioButtonViewModel.Event) {
        when (event) {
            is RadioButtonViewModel.Event.RadioButtonChecked -> {
                Toast.makeText(
                    requireContext(),
                    "RadioButtonChecked, index ${event.index} selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

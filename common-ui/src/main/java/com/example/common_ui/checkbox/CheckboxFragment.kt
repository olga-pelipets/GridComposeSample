package com.example.common_ui.checkbox

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
class CheckboxFragment : BaseFragment() {
    private val viewModel by viewModels<CheckboxViewModel>()

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
                        CheckboxScreen(viewModel)
                    }
                }
            }
        }
    }

    private fun handleEvent(event: CheckboxViewModel.Event) {
        when (event) {
            CheckboxViewModel.Event.CheckboxChecked -> {
                Toast.makeText(requireContext(), "CheckboxChecked", Toast.LENGTH_SHORT).show()
            }
            CheckboxViewModel.Event.CheckboxUnchecked -> {
                Toast.makeText(requireContext(), "CheckboxUnchecked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

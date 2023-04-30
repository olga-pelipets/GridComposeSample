package com.example.common_ui.typography

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.base.fragments.BaseFragment
import com.example.components.theme.GridTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypographyFragment : BaseFragment() {
    private val viewModel by viewModels<TypographyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycle.addObserver(viewModel)
        startCoroutineScope()

        return ComposeView(requireContext()).apply {
            setContent {
                GridTheme {
                    Surface {
                        TypographyScreen()
                    }
                }
            }
        }
    }
}

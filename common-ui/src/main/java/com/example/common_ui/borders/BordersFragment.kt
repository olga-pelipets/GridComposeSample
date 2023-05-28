package com.example.common_ui.borders

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
class BordersFragment : BaseFragment() {
    private val viewModel by viewModels<BordersViewModel>()

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
                        BordersScreen()
                    }
                }
            }
        }
    }
}

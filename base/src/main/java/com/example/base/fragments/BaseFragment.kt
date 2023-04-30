package com.example.base.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


open class BaseFragment : Fragment(), LifecycleObserver, DefaultLifecycleObserver {
    private lateinit var job: Job
    lateinit var uiScope: CoroutineScope

    fun startCoroutineScope() {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
    }

    override fun onDestroyView() {
        job.cancel()
        super.onDestroyView()
    }
}

package com.example.main_ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.fragments.BaseFragment
import com.example.components.theme.GridTheme
import com.example.main_ui.ui.MainWeatherScreen
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale


@AndroidEntryPoint
class MainScreenFragment : BaseFragment() {
    private val viewModel by viewModels<MainScreenViewModel>()

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

        val locationRequest: LocationRequest = LocationRequest.create()
        locationRequest.interval = 60000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val locationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult == null) {
                    return
                }
                for (location in locationResult.locations) {
                    if (location != null) {
                        //TODO: UI updates.
                    }
                }
            }
        }

        getFusedLocationProviderClient(requireActivity()).requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )

        checkPermission()

        return ComposeView(requireContext()).apply {
            setContent {
                GridTheme {
                    Surface {
                        MainWeatherScreen(viewModel)
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    checkGPS()
                }
            } else {
                alert(
                    "Allow permission for location to get weather in your region or select city",
                    "Location permission denied"
                )
            }
        }
    }

    private fun alert(message: String, title: String) {
        AlertDialog.Builder(requireContext()).apply {
            setMessage(message)
            setTitle(title)
            setPositiveButton(
                "ok"
            ) { _, _ ->
            }
        }
            .create()
            .show()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        getFusedLocationProviderClient(requireActivity()).lastLocation.addOnSuccessListener {
            val location = it
            try {
                val geocoder = Geocoder(requireActivity(), Locale.getDefault())
                val addresses: List<Address> = geocoder.getFromLocation(
                    location.latitude, location.longitude, 1
                )
                viewModel.storageRepository.saveCoordinates(
                    addresses.firstOrNull()?.latitude,
                    addresses.firstOrNull()?.longitude
                )
                viewModel.fetchData()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun checkGPS() {
        val manager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            alert("You need to have Location Services enabled!", "Location GPS is disabled")
        } else {
            getLocation()
        }
    }

    @Suppress("DEPRECATION")
    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) { // When permission denied at start
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
        } else {
            checkGPS()
        }
    }

    private fun handleEvent(event: MainScreenViewModel.Event) {
        when (event) {
            is MainScreenViewModel.Event.InfoDetails -> navigateToDetailsInfo(event.day, event.days)
        }
    }


    private fun navigateToDetailsInfo(day: String, days: List<String>) {
        findNavController().navigate(
            MainScreenFragmentDirections.navigateToViewPager(
                day,
                days.toTypedArray()
            )
        )
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 44
    }
}

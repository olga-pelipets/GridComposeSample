package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grid.compose.example.R
import com.grid.compose.example.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_WeatherApp)
        setContentView(binding.root)
    }
}

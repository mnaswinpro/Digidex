package com.digidex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digidex.databinding.ActivityDigimonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigimonActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDigimonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDigimonBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
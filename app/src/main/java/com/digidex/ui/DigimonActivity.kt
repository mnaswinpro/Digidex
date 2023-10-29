package com.digidex.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.digidex.R
import com.digidex.databinding.ActivityDigimonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigimonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDigimonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDigimonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(resources.getColor(R.color.black, null))
    }

    /**
     * Method to set status bar color for the [DigimonActivity]
     */
    private fun setStatusBarColor(@ColorInt color: Int) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }
}

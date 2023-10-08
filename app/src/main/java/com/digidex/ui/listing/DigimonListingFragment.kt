package com.digidex.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digidex.base.BaseFragment
import com.digidex.databinding.FragmentDigimonListingBinding

class DigimonListingFragment : BaseFragment() {

    private lateinit var binding : FragmentDigimonListingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDigimonListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {

    }

    companion object {
        fun newInstance() = DigimonListingFragment()
    }
}
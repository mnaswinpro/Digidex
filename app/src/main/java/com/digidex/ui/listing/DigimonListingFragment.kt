package com.digidex.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.digidex.base.BaseFragment
import com.digidex.databinding.FragmentDigimonListingBinding
import com.digidex.ui.DigimonViewModel
import com.digidex.domain.data.Digimon
import com.digidex.ui.common.DigimonListAdapter
import com.digidex.util.hide
import com.digidex.util.show

class DigimonListingFragment : BaseFragment() {

    private lateinit var binding : FragmentDigimonListingBinding

    private val viewModel : DigimonViewModel by activityViewModels()

    private val adapter by lazy { DigimonListAdapter(this::onDigimonSelected)}

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
        initObservers()
        if(isNetworkAvailable()) {
            viewModel.fetchDigimonList()
        }
    }

    private fun initUi() {
        binding.rvDigimonList.layoutManager = LinearLayoutManager(context)
        binding.rvDigimonList.adapter = adapter
    }

    private fun initObservers() {
        viewModel.digimonListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ListingScreen.Loading -> {
                    showLoading()
                }
                is ListingScreen.Empty -> TODO()
                is ListingScreen.Error -> TODO()
                is ListingScreen.Success -> {
                    showContent(it.digimonList)
                }
            }
        }
    }

    private fun showContent(list: List<Digimon>) {
        binding.progressBar.hide()
        binding.rvDigimonList.show()
        adapter.submitList(list)
    }

    private fun showLoading() {
        binding.rvDigimonList.hide()
        binding.progressBar.show()
    }

    private fun onDigimonSelected(digimon: Digimon) {

    }

    companion object {
        fun newInstance() = DigimonListingFragment()
    }
}
package com.digidex.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.digidex.base.BaseFragment
import com.digidex.databinding.FragmentDigimonListingBinding
import com.digidex.domain.data.Digimon
import com.digidex.ui.DigimonViewModel
import com.digidex.ui.common.DigimonListAdapter
import com.digidex.ui.common.DigimonListAdapter.Companion.GRID_LAYOUT_COLUMN_COUNT
import com.digidex.util.hide
import com.digidex.util.show

class DigimonListingFragment : BaseFragment() {

    private lateinit var binding: FragmentDigimonListingBinding

    private val viewModel: DigimonViewModel by activityViewModels()

    private val adapter by lazy { DigimonListAdapter(this::onDigimonSelected) }

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
        if (isNetworkAvailable()) {
            viewModel.fetchDigimonList()
        } else {
            //TODO
        }
    }

    private fun initUi() {
        binding.rvDigimonList.layoutManager = GridLayoutManager(context, GRID_LAYOUT_COLUMN_COUNT)
        binding.rvDigimonList.adapter = adapter
    }

    private fun initObservers() {
        viewModel.digimonListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ListingScreen.Loading -> {
                    showLoading()
                }

                is ListingScreen.Empty -> {
                    showEmpty()
                }

                is ListingScreen.Error -> {
                    showError()
                }

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

    private fun showError() {

    }

    private fun showEmpty() {

    }

    private fun onDigimonSelected(digimon: Digimon) {
        val action = DigimonListingFragmentDirections.actionListingToDetail(digimon = digimon)
        findNavController().navigate(action)
    }
}
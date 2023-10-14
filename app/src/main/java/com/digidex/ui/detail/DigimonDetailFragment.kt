package com.digidex.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.digidex.NavGraphArgs
import com.digidex.base.BaseFragment
import com.digidex.databinding.FragmentDigimonDetailBinding
import com.digidex.domain.data.Digimon
import com.digidex.domain.data.DigimonDetail
import com.digidex.domain.data.toDigimonDetail
import com.digidex.ui.DigimonViewModel
import com.digidex.ui.common.DigimonListAdapter
import com.digidex.ui.common.DigimonListAdapter.Companion.GRID_LAYOUT_COLUMN_COUNT
import com.digidex.util.hide
import com.digidex.util.isHidden
import com.digidex.util.loadImageAndShow
import com.digidex.util.setTextAndShow
import com.digidex.util.show

class DigimonDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDigimonDetailBinding

    private val viewModel: DigimonViewModel by activityViewModels()

    private var digimon: Digimon? = null

    private val priorEvolutionsAdapter by lazy { DigimonListAdapter(this::onDigimonSelected) }

    private val nextEvolutionsAdapter by lazy { DigimonListAdapter(this::onDigimonSelected) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDigimonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        arguments?.let { bundle ->
            digimon = NavGraphArgs.fromBundle(bundle).digimon
            digimon?.let { digimon ->
                loadDigimonDetail(digimon.toDigimonDetail())
                initObservers()
                viewModel.fetchDigimon(digimon.detailUrl)
            }
        }
    }

    private fun initUi() {
        binding.rvPriorEvolutions.layoutManager =
            GridLayoutManager(context, GRID_LAYOUT_COLUMN_COUNT)
        binding.rvPriorEvolutions.adapter = priorEvolutionsAdapter
        binding.rvPriorEvolutions.hide()
        binding.rvNextEvolutions.layoutManager =
            GridLayoutManager(context, GRID_LAYOUT_COLUMN_COUNT)
        binding.rvNextEvolutions.adapter = nextEvolutionsAdapter
        binding.rvNextEvolutions.hide()
    }

    private fun initObservers() {
        viewModel.digimonDetailLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DetailScreen.Success -> {
                    loadDigimonDetail(it.digimonDetail)
                }

                is DetailScreen.Error -> {
                    showError()
                }

                is DetailScreen.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun loadDigimonDetail(digimonDetail: DigimonDetail) {
        with(binding) {
            if (id.isHidden()) id.setTextAndShow(digimonDetail.id)
            if (name.isHidden()) name.setTextAndShow(digimonDetail.name)
            if (image.isHidden()) image.loadImageAndShow(digimonDetail.imageUrl)
            if (description.isHidden()) description.setTextAndShow(digimonDetail.description)
            if (digimonDetail.priorEvolutions.isNotEmpty()) {
                priorEvolutionsAdapter.submitList(digimonDetail.priorEvolutions)
                rvPriorEvolutions.show()
            }
            if (digimonDetail.nextEvolutions.isNotEmpty()) {
                nextEvolutionsAdapter.submitList(digimonDetail.nextEvolutions)
                rvNextEvolutions.show()
            }
        }
    }

    private fun showLoading() {

    }

    private fun showError() {

    }

    private fun onDigimonSelected(digimon: Digimon) {
        binding.rvPriorEvolutions.hide()
        binding.rvNextEvolutions.hide()
        val action = DigimonDetailFragmentDirections.actionDetailToDetail(digimon = digimon)
        findNavController().navigate(action)
    }
}
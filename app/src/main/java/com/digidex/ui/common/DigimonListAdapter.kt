package com.digidex.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.digidex.databinding.ItemDigimonBinding
import com.digidex.domain.data.Digimon

class DigimonListAdapter(private val onDigimonSelected: (Digimon) -> Unit) :
    ListAdapter<Digimon, DigimonListAdapter.DigimonViewHolder>(DigimonDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonViewHolder {
        val binding = ItemDigimonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DigimonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DigimonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DigimonViewHolder(private val binding: ItemDigimonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(digimon: Digimon) {
            binding.id.text = digimon.id
            binding.name.text = digimon.name
            binding.image.load(digimon.imageUrl)
            binding.root.setOnClickListener {
                onDigimonSelected(digimon)
            }
        }
    }

    object DigimonDiffUtil : DiffUtil.ItemCallback<Digimon>() {
        override fun areItemsTheSame(oldItem: Digimon, newItem: Digimon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Digimon, newItem: Digimon): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        const val GRID_LAYOUT_COLUMN_COUNT = 2
    }
}

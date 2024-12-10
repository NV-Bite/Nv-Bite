package com.example.NVBite.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.NVBite.data.models.HistoryResponseItem
import com.example.NVBite.data.models.Orphanage
import com.example.NVBite.databinding.ItemHistoryRowBinding
import com.example.NVBite.databinding.ItemMapsRowBinding

class MapAdapter : ListAdapter<Orphanage, MapAdapter.ViewHolder>(DIFF_CALLBACK) {
    var onMapClick: ((String) -> Unit)? = null
    var onPhoneClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMapsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { foodList ->
            foodList?.let { item ->
                holder.binding.tvTitle.text = item.name
                holder.binding.tvLocation.text = item.address


                holder.binding.btnMap.setOnClickListener {
                    onMapClick?.invoke(item.mapUrl)
                }

                holder.binding.btnPhone.setOnClickListener {
                    onPhoneClick?.invoke(item.phone)
                }
            }
        }
    }

    inner class ViewHolder(var binding: ItemMapsRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Orphanage>() {
            override fun areItemsTheSame(
                oldStories: Orphanage,
                newStories: Orphanage
            ): Boolean {
                return oldStories == newStories
            }

            override fun areContentsTheSame(
                oldStories: Orphanage,
                newStories: Orphanage
            ): Boolean {
                return oldStories == newStories
            }
        }
    }
}
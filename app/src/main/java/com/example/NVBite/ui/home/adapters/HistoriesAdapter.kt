package com.example.NVBite.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.NVBite.data.models.HistoryResponseItem
import com.example.NVBite.databinding.ItemHistoryRowBinding

class HistoriesAdapter : ListAdapter<HistoryResponseItem, HistoriesAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { foodList ->
            foodList?.let { item ->
                holder.binding.tvTitle.text = item.predictedClass
                holder.binding.tvPercentage.text = StringBuilder("Confidence Score : ${String.format("%.2f", item.confidence)}%")
            }
        }
    }

    inner class ViewHolder(var binding: ItemHistoryRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryResponseItem>() {
            override fun areItemsTheSame(
                oldStories: HistoryResponseItem,
                newStories: HistoryResponseItem
            ): Boolean {
                return oldStories == newStories
            }

            override fun areContentsTheSame(
                oldStories: HistoryResponseItem,
                newStories: HistoryResponseItem
            ): Boolean {
                return oldStories == newStories
            }
        }
    }
}
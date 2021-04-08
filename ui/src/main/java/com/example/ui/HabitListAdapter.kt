package com.example.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.HabitItem
import com.example.ui.databinding.HabitItemBinding

class HabitListAdapter :
    RecyclerView.Adapter<HabitListAdapter.ViewHolder>() {
    private var dataSet: List<HabitItem> = emptyList()

    inner class ViewHolder(private val binding: HabitItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(habitItem: HabitItem) {
            binding.habitItem = habitItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HabitItemBinding.inflate(
                LayoutInflater.from(viewGroup.context)
            )
        )

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun update(habitList: List<HabitItem>) {
        dataSet = habitList
        notifyDataSetChanged()
    }

}

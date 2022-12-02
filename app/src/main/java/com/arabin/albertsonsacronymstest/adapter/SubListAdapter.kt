package com.arabin.albertsonsacronymstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabin.albertsonsacronymstest.databinding.ItemsSubListItemBinding
import com.arabin.albertsonsacronymstest.retrofit.Lf

class SubListAdapter(items: List<Lf>) : RecyclerView.Adapter<SubListAdapter.SubViewHolder>() {

    private var mainList: List<Lf>? = null

    init {
        mainList = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
        return SubViewHolder(
            binding = ItemsSubListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        mainList?.get(position)?.let { holder.setDetails(it) }
    }

    override fun getItemCount(): Int {
        return mainList?.size!!
    }

    class SubViewHolder(private val binding: ItemsSubListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setDetails(item: Lf){
            binding.subName.text = item.lf
            binding.freq.text = item.freq.toString()
            binding.since.text = item.since.toString()
            binding.subList.apply {
                adapter = LastAdapter(item.vars)
            }
        }

    }

}
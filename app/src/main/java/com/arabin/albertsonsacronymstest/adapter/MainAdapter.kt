package com.arabin.albertsonsacronymstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arabin.albertsonsacronymstest.R
import com.arabin.albertsonsacronymstest.databinding.DetailsItemsCellBinding
import com.arabin.retrofitmodule.retrofit.ResponseItem

/**
 * @author Arabin
 * @since 12/01/2022
 * @sample AlbertSons
 * */


class MainAdapter(private val items: ArrayList<com.arabin.retrofitmodule.retrofit.ResponseItem>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.details_items_cell,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        items[position].let { holder.setDetails(it) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MainViewHolder(private val binding: DetailsItemsCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDetails(item: com.arabin.retrofitmodule.retrofit.ResponseItem) {
            binding.responseItem = item
            binding.subListAdapter = SubListAdapter(item.lfs)
        }

    }

}
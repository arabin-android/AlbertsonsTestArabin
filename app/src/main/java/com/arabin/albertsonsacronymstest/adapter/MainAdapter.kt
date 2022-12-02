package com.arabin.albertsonsacronymstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arabin.albertsonsacronymstest.R
import com.arabin.albertsonsacronymstest.databinding.DetailsItemsCellBinding
import com.arabin.albertsonsacronymstest.retrofit.ResponseItem

class MainAdapter(items: ArrayList<ResponseItem>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var mainList: List<ResponseItem>? = null

    init {
        mainList = items
    }

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
        mainList?.get(position)?.let { holder.setDetails(it) }
    }

    override fun getItemCount(): Int {
        return mainList?.size!!
    }

    class MainViewHolder(private val binding: DetailsItemsCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDetails(item: ResponseItem) {
            binding.responseItem = item
            binding.subListAdapter = SubListAdapter(item.lfs)
        }

    }

}
package com.arabin.albertsonsacronymstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arabin.albertsonsacronymstest.R
import com.arabin.albertsonsacronymstest.databinding.ItemsSubListItemBinding
import com.arabin.albertsonsacronymstest.retrofit.Lf

/**
 * @author Arabin
 * @since 12/01/2022
 * @sample AlbertSons
 * */


class SubListAdapter(items: List<Lf>) : RecyclerView.Adapter<SubListAdapter.SubViewHolder>() {

    private var mainList: List<Lf>? = null

    init {
        mainList = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
        return SubViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.items_sub_list_item,
                parent,
                false
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

        fun setDetails(item: Lf) {
            binding.lasAdapter = LastAdapter(item.vars)
        }

    }

}
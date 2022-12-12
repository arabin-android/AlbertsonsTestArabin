package com.arabin.albertsonsacronymstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arabin.albertsonsacronymstest.R
import com.arabin.albertsonsacronymstest.databinding.ItemsSubListItemBinding
import com.arabin.retrofitmodule.retrofit.Lf

/**
 * @author Arabin
 * @since 12/01/2022
 * @sample AlbertSons
 * */


class SubListAdapter(private val items: List<com.arabin.retrofitmodule.retrofit.Lf>) : RecyclerView.Adapter<SubListAdapter.SubViewHolder>() {

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
        items[position].let { holder.setDetails(it) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class SubViewHolder(private val binding: ItemsSubListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setDetails(item: com.arabin.retrofitmodule.retrofit.Lf) {
            binding.lasAdapter = LastAdapter(item.vars)
        }

    }

}
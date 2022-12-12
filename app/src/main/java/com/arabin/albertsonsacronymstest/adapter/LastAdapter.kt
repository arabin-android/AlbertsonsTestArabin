package com.arabin.albertsonsacronymstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arabin.albertsonsacronymstest.R
import com.arabin.albertsonsacronymstest.databinding.ItemsSubListItem2Binding
import com.arabin.retrofitmodule.retrofit.Var

/**
 * @author Arabin
 * @since 12/01/2022
 * @sample AlbertSons
 * */


class LastAdapter(private val items: List<com.arabin.retrofitmodule.retrofit.Var>) : RecyclerView.Adapter<LastAdapter.LastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastViewHolder {
        return LastViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.items_sub_list_item2,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LastViewHolder, position: Int) {
        items[position].let { holder.setDetails(it) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class LastViewHolder(private val binding: ItemsSubListItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setDetails(item: com.arabin.retrofitmodule.retrofit.Var) {
            binding.model = item
        }

    }

}
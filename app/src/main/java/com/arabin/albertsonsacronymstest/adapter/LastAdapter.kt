package com.arabin.albertsonsacronymstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabin.albertsonsacronymstest.databinding.ItemsSubListItem2Binding
import com.arabin.albertsonsacronymstest.retrofit.Var

class LastAdapter(items: List<Var>) : RecyclerView.Adapter<LastAdapter.LastViewHolder>() {


    private var mainList: List<Var>? = null

    init {
        mainList = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastViewHolder {
        return LastViewHolder(
            binding = ItemsSubListItem2Binding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LastViewHolder, position: Int) {
        mainList?.get(position)?.let { holder.setDetails(it) }
    }

    override fun getItemCount(): Int {
        return mainList?.size!!
    }

    class LastViewHolder(private val binding: ItemsSubListItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setDetails(item: Var){
            binding.lf.text = item.lf
            binding.freq.text = item.freq.toString()
            binding.since.text = item.since.toString()
        }

    }

}
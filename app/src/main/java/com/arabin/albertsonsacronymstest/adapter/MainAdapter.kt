package com.arabin.albertsonsacronymstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabin.albertsonsacronymstest.databinding.DetailsItemsCellBinding
import com.arabin.albertsonsacronymstest.retrofit.ResponseItem

class MainAdapter(items: ArrayList<ResponseItem>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    private var mainList: List<ResponseItem>? = null

    init {
        mainList = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            binding = DetailsItemsCellBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
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
            fun setDetails(item: ResponseItem){
                binding.mainMacro.text = item.sf
                binding.subMacro.apply {
                    adapter = SubListAdapter(item.lfs)
                }
            }

    }

}
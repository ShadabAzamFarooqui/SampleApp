package com.example.sampleapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.data.api.network_entity_model.Data
import com.example.sampleapp.databinding.SingleItemBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ListAdapter @ExperimentalCoroutinesApi constructor(
    private var languageList: List<Data>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {



    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(languageList[position]){
                binding.tvLangName.text = this.iso

                binding.tvDescription.text = this.name

                binding.expandedView.visibility = if (this.expand) View.VISIBLE else View.GONE

                binding.cardLayout.setOnClickListener {
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }
            }
        }
    }
    // return the size of languageList
    override fun getItemCount(): Int {
        return languageList.size
    }
}
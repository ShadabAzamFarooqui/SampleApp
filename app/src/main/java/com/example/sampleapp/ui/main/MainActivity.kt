package com.example.sampleapp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapp.data.api.network_entity_model.Data
import com.example.sampleapp.data.api.network_entity_model.NetworkMainResponse
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var listAdapter: ListAdapter
    private var mParentList = ArrayList<Data>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()

        subscribeObverser()
        viewModel.setStateEvent(MainStateEvent.GetNetworkData)

    }

    private fun setUI() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        listAdapter = ListAdapter(mParentList)
        binding.rvList.adapter = listAdapter

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun subscribeObverser() {
        viewModel.datastate.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<NetworkMainResponse> -> {
                    println(dataState.data.data);
                    binding.progressBar.visibility = View.GONE

                    mParentList.clear()
                    mParentList.addAll(dataState.data.data)
                    listAdapter.notifyDataSetChanged()


                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.GONE


                }
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
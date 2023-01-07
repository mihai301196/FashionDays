package com.mihaiim.fashiondays.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.RequestManager
import com.mihaiim.fashiondays.databinding.ActivityMainBinding
import com.mihaiim.fashiondays.others.EventObserver
import com.mihaiim.fashiondays.others.Resource
import com.mihaiim.fashiondays.ui.adapters.ClothingAdapter
import com.mihaiim.fashiondays.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var glide: RequestManager

    private lateinit var clothingAdapter: ClothingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservables()
        setupRecyclerView()

        viewModel.getWomenClothing()
    }

    private fun setupObservables() {
        viewModel.clothingResource.observe(this, EventObserver { clothingResult ->
            when (clothingResult) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    binding.srlClothing.isRefreshing = false
                }
                is Resource.Error -> {
                    binding.srlClothing.isRefreshing = false
                }
            }
        })

        viewModel.clothingList.observe(this) {
            clothingAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.srlClothing.setOnRefreshListener {
            viewModel.getWomenClothing()
        }

        clothingAdapter = ClothingAdapter(glide) {
            viewModel.removeClothing(it)
        }
        binding.rvClothing.adapter = clothingAdapter
    }
}
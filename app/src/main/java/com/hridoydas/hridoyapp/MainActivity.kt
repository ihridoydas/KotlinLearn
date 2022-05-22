package com.hridoydas.hridoyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hridoydas.hridoyapp.adapter.DogsPagingAdapter
import com.hridoydas.hridoyapp.databinding.ActivityMainBinding
import com.hridoydas.hridoyapp.databinding.EachRowBinding
import com.hridoydas.hridoyapp.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()

    @Inject
    lateinit var dogsPagingAdapter: DogsPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllDogs().collectLatest {response->
                binding.apply {
                    recyclerView.isVisible = true
                    progressBar.isVisible= false

                }
                dogsPagingAdapter.submitData(response)

            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity,2)
                adapter = dogsPagingAdapter


            }
        }
    }
}
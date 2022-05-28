package com.hridoydas.newpaging3


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hridoydas.newpaging3.Adapter.DogsAdapter
import com.hridoydas.newpaging3.Adapter.LoadingStateAdapter
import com.hridoydas.newpaging3.HiltPractice.Car
import com.hridoydas.newpaging3.HiltPractice.Main
import com.hridoydas.newpaging3.HiltPractice.Main2
import com.hridoydas.newpaging3.ViewModel.SecondViewModel

import com.hridoydas.newpaging3.databinding.ActivitySecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val secondViewModel: SecondViewModel by viewModels()

    @Inject
    lateinit var dogsAdapter: DogsAdapter
    @Inject
    lateinit var car: Car
    @Inject
    lateinit var main: Main
    @Inject
    lateinit var main2: Main2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        main.getName()
        main2.getName()
        car.getCar()

        initRecyclerView()

        val actionBar =supportActionBar
        actionBar!!.title= "Dagger Hilt and Paging 3"
        actionBar.setDisplayHomeAsUpEnabled(true)

        lifecycleScope.launchWhenCreated {

            secondViewModel.getAllDogs().collectLatest { response ->
                binding.apply {

                    recyclerview.isVisible = true
                    progressBar.isVisible = false
                }
                dogsAdapter.submitData(response)
            }
        }
    }

    private fun initRecyclerView() {

        binding.apply {

            recyclerview.apply {

                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@SecondActivity, 2)
                adapter = dogsAdapter
                adapter = dogsAdapter.withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter { dogsAdapter.retry() },
                    footer = LoadingStateAdapter { dogsAdapter.retry() }

                )
            }
        }

    }
}
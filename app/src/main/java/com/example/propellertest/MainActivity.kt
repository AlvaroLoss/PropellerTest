package com.example.propellertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propellertest.adapters.EventsAdapter
import com.example.propellertest.api.ApiService
import com.example.propellertest.api.ApiServiceProvider
import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.api.model.User
import com.example.propellertest.databinding.ActivityMainBinding
import com.example.propellertest.viewmodel.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userObserver = Observer<User> {
            if (it != null) {

            }
        }

        val eventsObserver = Observer<List<EventsItem>> {
            if (it != null) {
                setUpRecyclerView(it)
            }
        }

        viewModel = ViewModelProvider(this, ViewModelFactory.viewModelFactory {
            MainViewModel(ApiServiceProvider(ApiService()))
        }).get(MainViewModel::class.java)

        viewModel.userLiveData.observe(this, userObserver)
        viewModel.eventsLiveData.observe(this, eventsObserver)
    }

    private fun setUpRecyclerView(data: List<EventsItem>) {
        adapter = EventsAdapter(data)
        binding.eventsRv.layoutManager = LinearLayoutManager(this)
        binding.eventsRv.adapter = adapter
    }


}

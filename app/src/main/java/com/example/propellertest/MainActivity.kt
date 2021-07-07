package com.example.propellertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.propellertest.api.ApiService
import com.example.propellertest.api.ApiServiceProvider
import com.example.propellertest.databinding.ActivityMainBinding
import com.example.propellertest.viewmodel.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() { private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
//    private lateinit var adapter: HiringItemsAdapter
    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,
            ViewModelFactory.viewModelFactory {
                MainViewModel(ApiServiceProvider( ApiService())) })
            .get(MainViewModel::class.java)

        scope.launch {
            viewModel.eventsFlow.collect {
                if(it.isNotEmpty()){
                    it
                }
            }
        }
    }
}
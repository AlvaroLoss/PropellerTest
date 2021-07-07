package com.example.propellertest.viewmodel

import androidx.lifecycle.*
import com.example.propellertest.api.ApiServiceProvider
import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.api.model.User
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiServiceProvider: ApiServiceProvider
) : ViewModel(), IMainViewModel  {
    override val userLiveData: MutableLiveData<User> = MutableLiveData()
    override val eventsLiveData: MutableLiveData<List<EventsItem>> = MutableLiveData()

    init {
        viewModelScope.launch {
            apiServiceProvider.getEventsResponseWithOrderedEvents()
                .catch {
                    userLiveData.postValue(null)
                    eventsLiveData.postValue(null)
                }
                .collect {
                    userLiveData.postValue(it.user)
                    eventsLiveData.postValue(it.events)
                }
        }
    }
}
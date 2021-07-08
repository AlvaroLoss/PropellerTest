package com.example.propellertest.viewmodel

import androidx.lifecycle.*
import com.example.propellertest.api.ApiServiceProvider
import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.api.model.EventsResponse
import com.example.propellertest.utils.DateComparator
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    private val apiServiceProvider: ApiServiceProvider
) : ViewModel(), IMainViewModel  {
    override val userLiveData: MutableLiveData<EventsResponse> = MutableLiveData()
    override val updateEventsList: MutableLiveData<List<EventsItem>> = MutableLiveData()
    private var organizedEventsList = arrayListOf<EventsItem>()

    init {
        fetchEventsResponse()
    }

    override fun fetchEventsResponse() {
        viewModelScope.launch {
            apiServiceProvider.getEventsResponseWithOrderedEvents()
                .catch {
                    userLiveData.postValue(null)
                }
                .collect {
                    organizedEventsList = it.events
                    userLiveData.postValue(it)
                }
        }
    }

    override fun addEventInAscendingOrderByDate(event: EventsItem) {
        organizedEventsList.add(event)
        Collections.sort(organizedEventsList, DateComparator())
        updateEventsList.postValue(organizedEventsList)
    }
}
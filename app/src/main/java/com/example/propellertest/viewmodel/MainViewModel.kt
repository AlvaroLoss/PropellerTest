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
import kotlin.random.Random

class MainViewModel(
    private val apiServiceProvider: ApiServiceProvider
) : ViewModel(), IMainViewModel {
    override val userLiveData: MutableLiveData<EventsResponse> = MutableLiveData()
    override val updateEventsList: MutableLiveData<List<EventsItem>> = MutableLiveData()
    private var organizedEventsList = arrayListOf<EventsItem>()
    var addEventItem: EventsItem = EventsItem()

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

    override fun addEventInAscendingOrderByDate() {
        addEventItem.apply {
            id = (10238208 until 1023820898).random()
            uid = (10238208 until 1023820898).random().toString()
        }
        organizedEventsList.add(addEventItem)
        Collections.sort(organizedEventsList, DateComparator())
        updateEventsList.postValue(organizedEventsList)
    }

    override fun getAvailableMedicationNamesList(): List<String> {
        return userLiveData.value?.user?.medications?.map {
            it.name
        }!!
    }

    override fun setMedicationName(position: Int) {
        addEventItem.apply {
            medication = userLiveData.value?.user?.medications?.get(position)?.name!!
            medicationType = userLiveData.value?.user?.medications?.get(position)?.medicationType!!
        }
    }
}
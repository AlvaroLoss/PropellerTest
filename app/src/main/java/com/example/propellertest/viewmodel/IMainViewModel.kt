package com.example.propellertest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.api.model.EventsResponse
import com.example.propellertest.api.model.User
import kotlinx.coroutines.flow.StateFlow

interface IMainViewModel {
    val userLiveData: MutableLiveData<EventsResponse>
    val updateEventsList: MutableLiveData<List<EventsItem>>
    fun fetchEventsResponse()
    fun addEventInAscendingOrderByDate()
    fun setMedicationName(position: Int)
    fun getAvailableMedicationNamesList(): List<String>
}

package com.example.propellertest.api

import com.example.propellertest.api.model.EventsItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApiServiceProvider(
    private val apiService: ApiInterface
) : IApiServiceProvider {
    override fun getEventsByDate(): StateFlow<List<EventsItem>> {
        val mutableStateFLow = MutableStateFlow(emptyList<EventsItem>())
        GlobalScope.launch {
            val list =
                apiService.fetchEventData().events
//                    .filter { !it.name.isNullOrEmpty() }
//                    .sortedBy { it.name }
//                    .sortedBy { it.id }
            mutableStateFLow.emit(list)
        }
        return mutableStateFLow
    }
}
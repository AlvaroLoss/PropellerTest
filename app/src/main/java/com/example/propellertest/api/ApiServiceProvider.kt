package com.example.propellertest.api

import com.example.propellertest.utils.Utils
import com.example.propellertest.api.model.EventsResponse
import com.example.propellertest.utils.DateComparator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class ApiServiceProvider(
    private val apiService: ApiInterface
) : IApiServiceProvider {
    override fun getEventsResponseWithOrderedEvents(): Flow<EventsResponse> {
        return flow {
            val response = apiService.fetchEventData()
            response.events = response.events.sortedWith(DateComparator())
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}
package com.example.propellertest.api

import com.example.propellertest.api.model.EventsResponse
import com.example.propellertest.utils.DateComparator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*

class ApiServiceProvider(
    private val apiService: ApiInterface
) : IApiServiceProvider {
    override fun getEventsResponseWithOrderedEvents(): Flow<EventsResponse> {
        return flow {
            val response = apiService.fetchEventData()
            Collections.sort(response.events, DateComparator())
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}
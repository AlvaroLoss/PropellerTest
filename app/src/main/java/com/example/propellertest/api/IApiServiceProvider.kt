package com.example.propellertest.api

import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.api.model.EventsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IApiServiceProvider {
    fun getEventsResponseWithOrderedEvents(): Flow<EventsResponse>
}
package com.example.propellertest.api

import com.example.propellertest.api.model.EventsResponse
import kotlinx.coroutines.flow.Flow

interface IApiServiceProvider {
    fun getEventsResponseWithOrderedEvents(): Flow<EventsResponse>
}
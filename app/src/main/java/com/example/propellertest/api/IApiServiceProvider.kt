package com.example.propellertest.api

import com.example.propellertest.api.model.EventsItem
import kotlinx.coroutines.flow.StateFlow

interface IApiServiceProvider {
    fun getEventsByDate(): StateFlow<List<EventsItem>>
}
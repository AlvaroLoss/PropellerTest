package com.example.propellertest.api

import com.example.propellertest.api.model.EventsResponse

interface ApiInterface {
    suspend fun fetchEventData(): EventsResponse
}
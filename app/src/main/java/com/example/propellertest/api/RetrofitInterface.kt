package com.example.propellertest.api

import com.example.propellertest.api.model.EventsResponse
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("propeller_mobile_assessment_data.json")
    suspend fun fetchEvents(): EventsResponse
}
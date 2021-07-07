package com.example.propellertest.api

import com.example.propellertest.api.model.EventsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService: ApiInterface {

     private val hiringService by lazy {
        create()
    }

    private fun create(): RetrofitInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://s3-us-west-2.amazonaws.com/ph-svc-mobile-interview-jyzi2gyja/")
            .build()

        return retrofit.create(RetrofitInterface::class.java)
    }

    override suspend fun fetchEventData(): EventsResponse {
        return hiringService.fetchEvents()
    }
}
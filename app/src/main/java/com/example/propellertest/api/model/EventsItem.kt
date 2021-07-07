package com.example.propellertest.api.model

import com.google.gson.annotations.SerializedName

data class EventsItem(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("datetime")
    val datetime: String,

    @field:SerializedName("medicationtype")
    val medicationType: String,

    @field:SerializedName("medication")
    val medication: String,

    @field:SerializedName("id")
    val id: Int
)
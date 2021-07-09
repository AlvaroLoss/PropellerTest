package com.example.propellertest.api.model

import com.google.gson.annotations.SerializedName

data class EventsItem(
    @field:SerializedName("uid")
    var uid: String = "",

    @field:SerializedName("datetime")
    var datetime: String = "",

    @field:SerializedName("medicationtype")
    var medicationType: String = "",

    @field:SerializedName("medication")
    var medication: String = "",

    @field:SerializedName("id")
    var  id: Int = 0
)
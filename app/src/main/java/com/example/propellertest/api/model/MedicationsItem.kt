package com.example.propellertest.api.model

import com.google.gson.annotations.SerializedName

data class MedicationsItem(
    @field:SerializedName("medicationtype")
    val medicationType: String,

    @field:SerializedName("name")
    val name: String
)
package com.example.propellertest.api.model

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("disease")
    val disease: String,

    @field:SerializedName("address2")
    val address2: String,

    @field:SerializedName("address1")
    val address1: String,

    @field:SerializedName("dob")
    val dob: String,

    @field:SerializedName("sex")
    val sex: String,

    @field:SerializedName("medications")
    val medications: List<MedicationsItem>,

    @field:SerializedName("name")
    val name: String
)
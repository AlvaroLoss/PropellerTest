package com.example.propellertest.api.model

import com.google.gson.annotations.SerializedName

data class EventsResponse(
	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("events")
	val events: List<EventsItem>
)

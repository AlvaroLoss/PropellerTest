package com.example.propellertest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.api.model.User
import kotlinx.coroutines.flow.StateFlow

interface IMainViewModel {
    val userLiveData: MutableLiveData<User>
    val eventsLiveData: MutableLiveData<List<EventsItem>>
}

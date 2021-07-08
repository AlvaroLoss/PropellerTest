package com.example.propellertest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.databinding.EventsItemBinding
import com.example.propellertest.utils.Utils

class EventsAdapter(
    private var eventsList: List<EventsItem>
) : RecyclerView.Adapter<EventsAdapter.EventsItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsItemHolder {
        val binding = EventsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsItemHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsItemHolder, position: Int) {
        holder.bind(eventsList[position])
    }

    override fun getItemCount() = eventsList.size

    fun updateEventsList(data: List<EventsItem>){
        eventsList = data
        notifyDataSetChanged()
    }

    inner class EventsItemHolder constructor(private val binding: EventsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventsItem) {
            binding.eventId.text = "ID: " + item.uid
            binding.medicationName.text = "Medication Name: " + item.medication
            binding.medicationType.text = "Medication Type: " + item.medicationType
            binding.timestamp.text  = Utils.convertDateToStandard(item.datetime)
        }
    }
}
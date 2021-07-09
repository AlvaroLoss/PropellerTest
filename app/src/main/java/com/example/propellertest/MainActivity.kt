package com.example.propellertest

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propellertest.adapters.EventsAdapter
import com.example.propellertest.api.ApiService
import com.example.propellertest.api.ApiServiceProvider
import com.example.propellertest.api.model.EventsItem
import com.example.propellertest.api.model.EventsResponse
import com.example.propellertest.databinding.ActivityMainBinding
import com.example.propellertest.utils.DateUtils
import com.example.propellertest.viewmodel.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory.viewModelFactory {
            MainViewModel(ApiServiceProvider(ApiService()))
        }).get(MainViewModel::class.java)

        val userObserver = Observer<EventsResponse> {
            if (it != null) {
                setUpRecyclerView(it.events)
                binding.noData.visibility = View.GONE
            } else {
                binding.noData.visibility = View.VISIBLE
                binding.noData.text = getString(R.string.data_could_not_be_loaded)
            }
        }

        val eventsObserver = Observer<List<EventsItem>> {
            if (it != null) {
                adapter.updateEventsList(it)
            }
        }

        viewModel.userLiveData.observe(this, userObserver)
        viewModel.updateEventsList.observe(this, eventsObserver)
        viewModel.addEventItem.datetime.also { binding.selectedDate.text = it }
        binding.selectedDate.text =
            if (viewModel.addEventItem.datetime.isNullOrEmpty()) "" else
                DateUtils.convertDateToStandard(viewModel.addEventItem.datetime)
        binding.selectedMedicationName.text =
            if (viewModel.addEventItem.medication.isNullOrEmpty()) "" else
                viewModel.addEventItem.medication

        binding.dateTimeBt.setOnClickListener {
            pickDateTime()
        }
        binding.medicationNameBt.setOnClickListener {
            pickMedication()
        }

        binding.addEventBt.setOnClickListener {
            if (!binding.selectedMedicationName.text.isNullOrEmpty() &&
                !binding.selectedDate.text.isNullOrEmpty()
            ) {
                viewModel.addEventInAscendingOrderByDate()
                viewModel.addEventItem = EventsItem()
                binding.selectedMedicationName.text = ""
                binding.selectedDate.text = ""
                Toast.makeText(this, getString(R.string.add_event_successfully), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.fields_missing), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpRecyclerView(data: List<EventsItem>) {
        adapter = EventsAdapter(data)
        binding.eventsRv.layoutManager = LinearLayoutManager(this)
        binding.eventsRv.adapter = adapter
    }

    private fun pickMedication() {
        val builder = AlertDialog.Builder(this)
        val listAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, viewModel.getAvailableMedicationNamesList()
        )

        builder.setTitle(R.string.medication_title_dialogue).setAdapter(
            listAdapter
        ) { _, which ->
            val medication = listAdapter.getItem(which)!!
            viewModel.setMedicationName(listAdapter.getPosition(medication))
            binding.selectedMedicationName.text = medication
        }.setPositiveButton(R.string.OK) { dialog, _ ->
            dialog.dismiss()
        }.setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }.create()

        builder.show()
    }

    private fun pickDateTime() {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(this, { _, year, month, day ->
            TimePickerDialog(this, { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                val selectedDate = DateUtils.format.format(pickedDateTime.time)
                viewModel.addEventItem.datetime = selectedDate
                binding.selectedDate.text = DateUtils.convertDateToStandard(selectedDate)
            }, startHour, startMinute, false).show()
        }, startYear, startMonth, startDay).show()
    }
}

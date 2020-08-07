package com.example.plazitconfpractice.view.adapter


import com.example.plazitconfpractice.model.Conference
import com.example.plazitconfpractice.model.Speaker
import java.text.FieldPosition

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}
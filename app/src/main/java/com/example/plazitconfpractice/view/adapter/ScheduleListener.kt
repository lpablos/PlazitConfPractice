package com.example.plazitconfpractice.view.adapter


import com.example.plazitconfpractice.model.Conference
import java.text.FieldPosition

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}
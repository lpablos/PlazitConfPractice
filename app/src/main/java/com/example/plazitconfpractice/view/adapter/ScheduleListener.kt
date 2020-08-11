package com.example.plazitconfpractice.view.adapter


import com.example.plazitconfpractice.model.Conference


interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}
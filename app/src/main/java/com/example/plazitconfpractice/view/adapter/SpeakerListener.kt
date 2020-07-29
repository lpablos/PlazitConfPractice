package com.example.plazitconfpractice.view.adapter

import com.example.plazitconfpractice.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}
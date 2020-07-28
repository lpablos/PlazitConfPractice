package com.example.plazitconfpractice.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plazitconfpractice.R
import com.example.plazitconfpractice.model.Conference
import com.google.android.gms.signin.internal.SignInClientImpl
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter (scheduleListener: ScheduleAdapter) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){
    var listConference = ArrayList<Conference>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        // Indicamos cual es el archivo que vamos a usar para este adaptador
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        // verifica el numero de elementos
        return listConference.size
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conferencia = listConference[position] as Conference
        holder.tvConferenceName.text = conferencia.title
        holder.tvConferenceSpeaker.text = conferencia.speaker
        holder.tvConferenceTag.text = conferencia.tag

        val simpleDateformat = SimpleDateFormat("HH:mm")
        val simpleDateformatAMPM = SimpleDateFormat("a")
        val cal = Calendar.getInstance()
        cal.time = conferencia.datetime
        val hourFormat = simpleDateformat.format(conferencia.datetime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateformatAMPM.format(conferencia.datetime).toUpperCase()
    }

    fun updateData (data: List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvConferenceName = itemView.findViewById<TextView>(R.id.ScheduleConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.ScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.itemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.itemScheduleAMPM)
    }

}
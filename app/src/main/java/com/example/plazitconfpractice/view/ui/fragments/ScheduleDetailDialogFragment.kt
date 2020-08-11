package com.example.plazitconfpractice.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.example.plazitconfpractice.R
import com.example.plazitconfpractice.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1.- Estado de la aplicación para el style creado para el dialogo
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 1.1.- ponerle al toolbar anexar un ikono
        toolbarConference.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        // 1.2.- Cambiarle el color al titulo para hacerlo visible
        toolbarConference.setTitleTextColor(Color.WHITE)
        // 1.3.- selecionar el boton hacer que se cierrre
        toolbarConference.setNavigationOnClickListener {
            dismiss()
        }

        // 2.1.- Obtencion de argumentos
        val conference = arguments?.getSerializable("conference") as Conference
        // 2.2.- Definir del objecto el nombre en el toolbar
        toolbarConference.title = conference.title
        // 2.3.- Rellendar los elementos del layout con sus respectivos elementos del objecto
        titleScheduleDetail.text = conference.title

        //-----------------------------------
        // 2.4 Definicion del patron, definir el simple formato con el patron y finalmente aplicar el formato al objecto
        val patron  = "dd/MM/yyyy hh:mm a"
        val simpleDF = SimpleDateFormat(patron)
        val fecha = simpleDF.format(conference.datetime)
        //-----------------------------------

        ScheduleDetailConfHour.text = fecha
        ScheduleDetailConfSpeaker.text = conference.speaker
        ScheduleDetailConfTag.text = conference.tag
        ScheduleDetailConfDescription.text = conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}

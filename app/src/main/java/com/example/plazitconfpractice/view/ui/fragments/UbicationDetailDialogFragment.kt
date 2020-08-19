package com.example.plazitconfpractice.view.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import android.graphics.Color
import android.net.Uri
import android.util.Log
import com.example.plazitconfpractice.R
import com.example.plazitconfpractice.model.Ubication
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class UbicationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1.- Estado de la aplicación para el style creado para el dialogo
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("Clikeado", "Hola desde el detalle de la vista")
        // 1.1.- ponerle al toolbar anexar un ikono
        toolbarUbication.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        // 1.2.- Cambiarle el color al titulo para hacerlo visible
        toolbarUbication.setTitleTextColor(Color.WHITE)
        // 1.3.- selecionar el boton hacer que se cierrre
        toolbarUbication.setNavigationOnClickListener {
            dismiss()
        }
        // 1.4.- asiganar los valores correspondientes a la sección
        val ubicacion  = Ubication()
        toolbarUbication.title = ubicacion.name
        ScheduleDetailNombreLugar.text = ubicacion.name
        tvUbicationDireccion.text = ubicacion.address
        tvUbicationTelefono.text = ubicacion.phone
        tvUbicationWebsite.text = ubicacion.website

        // 1.5 Agregar un evento a un apartado de la vista
        llTelefonoLugar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel : ${ubicacion.phone}")
            }
            startActivity(intent)
        }
        llSiteweb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(ubicacion.website)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        // Definicion de las proporciones a mostrar
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }



}

package com.example.plazitconfpractice.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.plazitconfpractice.R
import com.example.plazitconfpractice.model.Speaker

import com.example.plazitconfpractice.view.adapter.SpeakerAdapter
import com.example.plazitconfpractice.view.adapter.SpeakerListener
import com.example.plazitconfpractice.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(), SpeakerListener{
    private lateinit var speakerAdapter : SpeakerAdapter
    private lateinit var viewModel : SpeakerViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 1.- Llamado al modelo
        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()
        // 2.- Llamado al adaptador del recicleview
        speakerAdapter = SpeakerAdapter(this)
        // 3.- configuracion para el fragment que usara esta configuracion y aplicar la configuracion
        rvSpeakers.apply {
            //como se muestra la informarion en el listado
            layoutManager = GridLayoutManager(context,2 )
            // Definir su adaptador del recicleView
            adapter = speakerAdapter
        }
        // 4.- El observador del  que creamos vieModel
        observeViewModel()
    }

    fun observeViewModel(){
        // 1.1.- Llamada del ViewModel y al listado del mismo
        viewModel.listSpeaker.observe(this, Observer<List<Speaker>>{ speakers->
            // 1.2.- Llamada del adaptador para cargar los datos
            speakers.let {
                speakerAdapter.updateData(speakers)
            }

        })
        // 2.1 Llamada para quitar el load al terminar la carga de datos
        viewModel.isLoading.observe(this,Observer<Boolean>{
            // 2.2 verificamos que sea falso y llamamos al elmento a oculatar
            if (it != null)
                rlBase.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        // 1.- enviar un objecto mediante un bundle
        var bundle = bundleOf("speaker" to speaker)
        // 2.- buscamos navegar con la onfiguracion y el id de la direcion de navegacion
        findNavController().navigate(R.id.speakerDetailFragmentDialog, bundle)
    }


}

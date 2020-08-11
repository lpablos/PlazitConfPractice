package com.example.plazitconfpractice.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.plazitconfpractice.R
import com.example.plazitconfpractice.model.Conference
import com.example.plazitconfpractice.view.adapter.ScheduleAdapter
import com.example.plazitconfpractice.view.adapter.ScheduleListener
import com.example.plazitconfpractice.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(), ScheduleListener {
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()
        scheduleAdapter = ScheduleAdapter(this)
        // identificador de recicle view en el fragmen
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }
        observeViewModel()
    }

    fun observeViewModel(){
        // es la conexxion
        viewModel.listSchedule.observe(this, Observer<List<Conference>>{schedule->
            // adaptador del recicle view
            schedule.let {
                scheduleAdapter.updateData(schedule)
            }
        })
        // Trabajando con mostrar y ocultar el loading que se tiene
        viewModel.isLoading.observe(this, Observer<Boolean>{
            if(it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }
    // Evento del click
    override fun onConferenceClicked(conference: Conference, position: Int) {
        //envio de objecto por medio de un bundle al click se enviara on objecto de conferencia
        val bundle = bundleOf("conference" to conference)
        // llamamos la navegacion, este hace referencia a las rutas de la navegacion en la carpeta de navigation
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }

}

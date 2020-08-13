package com.example.plazitconfpractice.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.plazitconfpractice.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

/**
 * A simple [Fragment] subclass.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        /*val UPV = LatLng(39.481106, -0.340987) //Nos ubicamos en la UPV
        googleMap.addMarker(MarkerOptions().position(UPV).title("Marker UPV"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(UPV))*/
    }

}

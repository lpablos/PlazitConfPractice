package com.example.plazitconfpractice.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController

import com.example.plazitconfpractice.R
import com.example.plazitconfpractice.model.Ubication
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


/**
 * A simple [Fragment] subclass.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        // 1.- Agregar el modelo
        val ubication = Ubication()
        // 2.- Agregar la posicion del mapa
        val zoom = 16f
        val centerMap = LatLng(ubication.latitude, ubication.longitude)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))

        // 3.- Agregar el marcado dentro del mapa
        val centerMark = LatLng(ubication.latitude, ubication.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMark)
        markerOptions.title("Platzi Conf 2019")
        // 3.x Agregar un pign personalizado
        // 3.x.1 Conversion de la imagen para ser utilizada
        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable
        // 3.x.2 Agregar dimension personalizadas para ser mostrada
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150, 150, false)
        // 3.x.3 Se genera el icono
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        // 3.x.4 se añade al mapa
        googleMap?.addMarker(markerOptions)
        googleMap?.setOnMarkerClickListener(this)
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.google_style))
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        // 1.- Se define a donde va a ir con el id de navegacion
        //findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true

    }


}

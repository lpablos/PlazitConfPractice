package com.example.plazitconfpractice.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.plazitconfpractice.R
import com.example.plazitconfpractice.model.Speaker
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersDetailDialogFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1.- Estado de la aplicación para el style creado para el dialogo
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarDetailDialog.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarDetailDialog.setTitleTextColor(Color.WHITE)
        toolbarDetailDialog.setNavigationOnClickListener {
            dismiss()
        }
        // Obtencion de los datos
        val speaker = arguments?.getSerializable("speaker") as Speaker

        toolbarDetailDialog.title = speaker.name
        // definicion de la image
        Glide.with(ivDetailSpeakerImage.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(ivDetailSpeakerImage)
        // definicion de los valies en el layout
        tvDetailSpeakerName.text = speaker.name
        tvDetailSpeakerJobtitle.text = speaker.jobtitle
        tvDetailSpeakerWorkplace.text = speaker.workplace
        tvDetailSpeakerBiography.text = speaker.biography
    }

    override fun onStart() {
        super.onStart()
        // Definicion de las proporciones a mostrar
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}

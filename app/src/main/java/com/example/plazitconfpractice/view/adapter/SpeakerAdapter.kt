package com.example.plazitconfpractice.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.plazitconfpractice.R
import com.example.plazitconfpractice.model.Speaker

class SpeakerAdapter(val speakerListener: SpeakerListener): RecyclerView.Adapter<SpeakerAdapter.ViewHolder>(){
    val listSpeakers = ArrayList<Speaker>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listSpeakers.size
    }

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        val speaker = listSpeakers[position] as Speaker
        holder.tvSpeakerNombre.text = speaker.name
        holder.tvSpeakerWork.text = speaker.workplace

        // Aqui la ponemos  Glide para usar
        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.tvSpeakerImagen)
        // Este es el evento de click con el uso de la interface
        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerClicked(speaker, position)
        }
    }

    fun updateData (data: List<Speaker>){
        listSpeakers.clear()
        listSpeakers.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvSpeakerNombre = itemView.findViewById<TextView>(R.id.tvItemSpeakerName)
        val tvSpeakerWork = itemView.findViewById<TextView>(R.id.tvItemSpeakerWork)
        val tvSpeakerImagen = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)

    }

}
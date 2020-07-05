package com.example.plazitconfpractice.network

import com.example.plazitconfpractice.model.Conference
import com.example.plazitconfpractice.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val CONFERENCE_COLLECTION_NAME ="conferences"
const val SPEAKER_COLLECTION_NAME = "speaker"
class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        // Datos descagados primera vez seran persistente aun sin tener internet
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSpeaker(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection(SPEAKER_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->{
                for (doc in result){
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSucces(list)
                    break
                }
            } }
    }
    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCE_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result -> {
                for (doc in result){
                    var list = result.toObjects(Conference::class.java)
                    callback.onSucces(list)
                    break
                }
            } }

    }
}
package com.example.plazitconfpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.plazitconfpractice.model.Conference
import com.example.plazitconfpractice.network.Callback
import com.example.plazitconfpractice.network.FirestoreService
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class ScheduleViewModel {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object : Callback<List<Conference>>{
            override fun onSucces(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinish()
            }

            override fun onFail(exception: Exception) {
                processFinish()
            }
        })
    }

    fun processFinish(){
        isLoading.value = true
    }
}
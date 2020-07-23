package com.example.plazitconfpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.plazitconfpractice.model.Speaker
import com.example.plazitconfpractice.network.Callback
import com.example.plazitconfpractice.network.FirestoreService
import java.lang.Exception
import java.util.*

class SpeakerViewModel {
    val fireStoreService = FirestoreService()
    var listSpeaker : MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading  = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase(){
        fireStoreService.getSpeaker(object : Callback<List<Speaker>>{
            override fun onSucces(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                processFinish()
            }

            override fun onFail(exception: Exception) {
                processFinish()
            }
        })
    }

    fun processFinish(){
        isLoading.value = false
    }
}
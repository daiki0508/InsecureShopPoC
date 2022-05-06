package com.websarva.wings.android.insecureshoppoc.viewmodel.fragment

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.websarva.wings.android.insecureshoppoc.repository.PlatformRepositoryClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlatformViewModel @Inject constructor(
    private val platformRepository: PlatformRepositoryClient,
    application: Application
) : AndroidViewModel(application) {
    private val _receiveData = MutableLiveData<String>()
    val receiveData: LiveData<String> = Transformations.map(_receiveData){
        it
    }
    private val _resultDump = MutableLiveData<String>()
    val resultDump: LiveData<String> = Transformations.map(_resultDump){
        it
    }

    fun setData(intent: Intent){
        _receiveData.value = "username: ${intent.getStringExtra("username")}\npassword: ${intent.getStringExtra("password")}"
    }
    fun dump(uri: Uri){
        platformRepository.dump(getApplication<Application>().applicationContext, uri){
            _resultDump.value = it
        }
    }
}
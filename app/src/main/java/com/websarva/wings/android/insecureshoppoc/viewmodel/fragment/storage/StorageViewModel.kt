package com.websarva.wings.android.insecureshoppoc.viewmodel.fragment.storage

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.websarva.wings.android.insecureshoppoc.repository.StorageRepositoryClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val storageRepositoryClient: StorageRepositoryClient,
    application: Application
) : AndroidViewModel(application) {
    private val _resultDump = MutableLiveData<String>()
    val resultDump: LiveData<String> = Transformations.map(_resultDump){
        it
    }

    fun startLog(){
        storageRepositoryClient.startLog(getApplication<Application>().applicationContext)
    }
    fun finishLog(){
        storageRepositoryClient.finishLog()
    }
    fun dump(uri: Uri){
        storageRepositoryClient.dump(getApplication<Application>().applicationContext, uri){
            _resultDump.value = it
        }
    }
}
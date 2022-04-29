package com.websarva.wings.android.insecureshoppoc.viewmodel.fragment.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.websarva.wings.android.insecureshoppoc.repository.StorageRepositoryClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Storage3ViewModel @Inject constructor(
    private val storageRepositoryClient: StorageRepositoryClient,
    application: Application
) : AndroidViewModel(application) {
    fun startLog(){
        storageRepositoryClient.startLog(getApplication<Application>().applicationContext)
    }
    fun finishLog(){
        storageRepositoryClient.finishLog()
    }
}
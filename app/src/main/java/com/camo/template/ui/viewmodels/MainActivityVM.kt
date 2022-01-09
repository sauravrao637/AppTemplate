package com.camo.template.ui.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camo.template.database.repos.Repository
import com.camo.template.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val cgRepo: Repository
) : ViewModel() {
    private val _pingState = MutableStateFlow<Resource<Any>>(Resource.loading())
    val pingState: StateFlow<Resource<Any>> get() = _pingState

    init {
        ping()
    }

    private var _pingJob: Job? = null
    fun ping(){
        Timber.d("pinging server")
        _pingJob?.cancel()
        _pingJob =viewModelScope.launch {
            cgRepo.pingCG().collect {
                _pingState.value = it
                Timber.d(it.toString())
            }
        }
    }
}
package com.worldimage.carfax.data.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worldimage.carfax.data.api.ApiRepository
import com.worldimage.carfax.data.model.Listings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel(){

    fun getVehicleList(): LiveData<List<Listings>> {
        return repository.getVehicleList()
    }

    fun makeApiCall(){
        repository.makeApiCall()
    }
}

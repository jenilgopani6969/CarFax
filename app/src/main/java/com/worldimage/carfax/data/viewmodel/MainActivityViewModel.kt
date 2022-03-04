package com.worldimage.carfax.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worldimage.carfax.data.api.ApiService
import com.worldimage.carfax.data.response.VehicleListResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityViewModel : ViewModel(){

    var vehicleList:  MutableLiveData<VehicleListResponse> = MutableLiveData()

    fun getVehicleListObserver(): MutableLiveData<VehicleListResponse> {
        return vehicleList
    }

    fun makeApiCall(){
        ApiService.invoke().getCarListing().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<VehicleListResponse> {
                override fun onSubscribe(d: Disposable) {
                    Log.e("MainActivity", "OnSubscribe")
                    //start progress indicator
                }

                override fun onNext(t: VehicleListResponse) {
                    Log.e("MainActivity", "OnNext + ${t.listings[0].engine}")
                    vehicleList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    vehicleList.postValue(null)
                    Log.e("MainActivity", "OnError")
                }

                override fun onComplete() {
                    //stop progress indicator
                    Log.e("MainActivity", "OnCompete")
                }

            })
    }

}
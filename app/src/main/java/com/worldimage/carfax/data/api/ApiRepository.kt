package com.worldimage.carfax.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import com.worldimage.carfax.data.db.AppDao
import com.worldimage.carfax.data.model.Listings
import com.worldimage.carfax.data.model.VehicleListResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val appDao: AppDao) {

    fun getVehicleList(): LiveData<List<Listings>> {
        return appDao.getAllRecords()
    }

    fun insertVehicleList(listings: Listings) {
        appDao.insertRecords(listings)
    }

    //set Observer from api
    fun makeApiCall(){
        apiInterface.getVehicleListing().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<VehicleListResponse> {
                override fun onSubscribe(d: Disposable) {
                    Log.e("MainActivity", "OnSubscribe")
                    //start progress indicator
                }

                override fun onNext(t: VehicleListResponse) {
                    Log.e("MainActivity", "OnNext")
                    //delete room db
                    appDao.deleteAllRecords()
                    //add new entry to room db
                    t.listings.forEach {
                        insertVehicleList(it)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("MainActivity", "OnError + $e")
                }

                override fun onComplete() {
                    //stop progress indicator
                    Log.e("MainActivity", "OnCompete")
                }

            })
    }

}
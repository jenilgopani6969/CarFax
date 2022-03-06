package com.worldimage.carfax.data.api

import com.worldimage.carfax.data.model.VehicleListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("assignment.json")
    fun getVehicleListing(): Observable<VehicleListResponse>

}
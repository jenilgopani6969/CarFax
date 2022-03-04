package com.worldimage.carfax.data.api

import com.worldimage.carfax.data.response.VehicleListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("assignment.json")
    fun getCarListing(): Observable<VehicleListResponse>

}
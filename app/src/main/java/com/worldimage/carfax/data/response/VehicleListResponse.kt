package com.worldimage.carfax.data.response

const val VEHICLE_LIST_ID = 0

data class VehicleListResponse(
    val listings: List<Listings>
)

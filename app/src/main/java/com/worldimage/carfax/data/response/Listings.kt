package com.worldimage.carfax.data.response

data class Listings(
    val bodytype: String,
    val dealer: Dealer,
    val drivetype: String,
    val engine: String,
    val exteriorColor: String,
    val fuel: String,
    val images: Images,
    val interiorColor: String,
    val listPrice: Int,
    val make: String,
    val mileage: Int,
    val model: String,
    val transmission: String,
    val trim: String,
    val year: Int
)
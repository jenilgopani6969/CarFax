package com.worldimage.carfax.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicleList")
class Listings(
    @ColumnInfo(name = "bodytype")val bodytype: String,
    @Embedded(prefix = "dealer_")val dealer: Dealer,
    @ColumnInfo(name = "drivetype")val drivetype: String,
    @ColumnInfo(name = "engine")val engine: String,
    @ColumnInfo(name = "exteriorColor")val exteriorColor: String,
    @ColumnInfo(name = "fuel")val fuel: String,
    @Embedded(prefix = "image_")val images: Images,
    @ColumnInfo(name = "interiorColor")val interiorColor: String,
    @ColumnInfo(name = "listPrice")val listPrice: Int,
    @ColumnInfo(name = "make")val make: String,
    @ColumnInfo(name = "mileage")val mileage: Int,
    @ColumnInfo(name = "model")val model: String,
    @ColumnInfo(name = "transmission")val transmission: String,
    @ColumnInfo(name = "trim")val trim: String,
    @ColumnInfo(name = "year")val year: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("table_id") var table_id: Int = 0
}
package com.worldimage.carfax.data.model

import androidx.room.ColumnInfo

data class Dealer(
    @ColumnInfo(name = "city")val city: String,
    @ColumnInfo(name = "phone")val phone: String,
    @ColumnInfo(name = "state")val state: String
)
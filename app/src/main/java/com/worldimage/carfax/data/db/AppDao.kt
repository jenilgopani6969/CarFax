package com.worldimage.carfax.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.worldimage.carfax.data.model.Listings

@Dao
interface AppDao {

    @Query("SELECT * FROM vehicleList")
    fun getAllRecords(): LiveData<List<Listings>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(listings: Listings)

    @Query("DELETE FROM vehicleList")
    fun deleteAllRecords()

}
package com.worldimage.carfax.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.worldimage.carfax.R
import com.worldimage.carfax.data.adapter.VehicleListAdapter
import com.worldimage.carfax.data.model.Listings
import com.worldimage.carfax.data.model.VehicleListResponse
import com.worldimage.carfax.data.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

    private lateinit var vehicleListAdapter: VehicleListAdapter
    private lateinit var recyclerView: RecyclerView
    private val requestCall = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init
        recyclerView = findViewById<RecyclerView>(R.id.recycleView)

        initMainViewModel()

        //RecycleViewAdapter
        vehicleListAdapter = VehicleListAdapter()
        recyclerView.adapter = vehicleListAdapter

        //ask Permission
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.CALL_PHONE),
                requestCall
            )
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMainViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getVehicleList().observe(this,Observer<List<Listings>>{
            if (it != null){
                //update adapter
                vehicleListAdapter.setVehicleListData(it)
                vehicleListAdapter.notifyDataSetChanged()

            } else {
                Toast.makeText(this, "Error while fetching data!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

}




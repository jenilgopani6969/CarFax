package com.worldimage.carfax.ui

import android.Manifest
import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.worldimage.carfax.R
import com.worldimage.carfax.data.adapter.VehicleListAdapter
import com.worldimage.carfax.data.response.Listings
import com.worldimage.carfax.data.response.VehicleListResponse
import com.worldimage.carfax.data.viewmodel.MainActivityViewModel

class MainActivity() : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var vehicleListAdapter: VehicleListAdapter
    lateinit var recyclerView: RecyclerView
    private val requestCall = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pojo
        recyclerView = findViewById<RecyclerView>(R.id.recycleView)

        loadApiData()

        //RecycleViewAdapter
        vehicleListAdapter = VehicleListAdapter()
        recyclerView.adapter = vehicleListAdapter

        //clickListner


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
    private fun loadApiData() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getVehicleListObserver().observe(this,Observer<VehicleListResponse>{
            if (it != null){
                //update adapter
                vehicleListAdapter.vehicleList = it.listings
                vehicleListAdapter.notifyDataSetChanged()
                Log.e("MainActivity", it.listings[0].engine)
            } else {
                Toast.makeText(this, "Error while fetching data!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }

}




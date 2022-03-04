package com.worldimage.carfax.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pojo
        recyclerView = findViewById<RecyclerView>(R.id.recycleView)

        loadApiData()

        //RecycleViewAdapter
        vehicleListAdapter = VehicleListAdapter()
        recyclerView.adapter = vehicleListAdapter


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




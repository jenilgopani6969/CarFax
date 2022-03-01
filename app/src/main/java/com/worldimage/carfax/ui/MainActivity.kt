package com.worldimage.carfax.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.worldimage.carfax.R
import com.worldimage.carfax.data.ApiService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = ApiService()

        //pojo
        val txtView = findViewById<TextView>(R.id.txtview)

        GlobalScope.launch(Dispatchers.Main) {
            val apiResponse = apiService.getCarListing().await()
            txtView.text = apiResponse.listings[0].model
        }

    }
}

package com.worldimage.carfax.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.worldimage.carfax.R
import java.text.NumberFormat

class DetailedActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        //pojo
        val image_view_photo_large = findViewById<ImageView>(R.id.image_view_photo_large)
        val text_view_year_make_model = findViewById<TextView>(R.id.text_view_year_make_model)
        val text_view_price = findViewById<TextView>(R.id.text_view_price)
        val text_view_mileage = findViewById<TextView>(R.id.text_view_mileage)
        val text_view_location = findViewById<TextView>(R.id.text_view_location)
        val text_view_exterior_color = findViewById<TextView>(R.id.text_view_exterior_color)
        val text_view_interior_color = findViewById<TextView>(R.id.text_view_interior_color)
        val text_view_drive_type = findViewById<TextView>(R.id.text_view_drive_type)
        val text_view_transmission = findViewById<TextView>(R.id.text_view_transmission)
        val text_view_body_style = findViewById<TextView>(R.id.text_view_body_style)
        val text_view_engine = findViewById<TextView>(R.id.text_view_engine)
        val text_view_fuel = findViewById<TextView>(R.id.text_view_fuel)
        val button_call_dealer = findViewById<AppCompatButton>(R.id.button_call_dealer)

        //getData
        val intent: Intent = intent
        val ls_photo = intent.getStringExtra("ls_photo")
        val ls_year = intent.getStringExtra("ls_year")
        val ls_make = intent.getStringExtra("ls_make")
        val ls_model = intent.getStringExtra("ls_model")
        val ls_trim = intent.getStringExtra("ls_trim")
        val ls_price = intent.getStringExtra("ls_price")
        val ls_mileage = intent.getStringExtra("ls_mileage")
        val ls_location_city = intent.getStringExtra("ls_location_city")
        val ls_location_state = intent.getStringExtra("ls_location_state")
        val ls_call = intent.getStringExtra("ls_call")
        val ls_bodytype = intent.getStringExtra("ls_bodytype")
        val ls_ex_color = intent.getStringExtra("ls_ex_color")
        val ls_in_color = intent.getStringExtra("ls_in_color")
        val ls_drivetype = intent.getStringExtra("ls_drivetype")
        val ls_trans = intent.getStringExtra("ls_trans")
        val ls_engine = intent.getStringExtra("ls_engine")
        val ls_fuel = intent.getStringExtra("ls_fuel")

        //setData
        text_view_year_make_model.text = "$ls_year $ls_make $ls_model $ls_trim"

        val price: Int = ls_price!!.toInt()
        val numberFormat = NumberFormat.getNumberInstance()
        numberFormat.maximumFractionDigits = 0
        val convert = numberFormat.format(price)
        text_view_price.text = "$ $convert"

        val convMiles: Double = (ls_mileage!!.toDouble()/1000)
        val convertedMiles:Double = String.format("%.1f", convMiles).toDouble()
        text_view_mileage.text = "${convertedMiles}k mi"

        text_view_location.text = "$ls_location_city, $ls_location_state"
        text_view_exterior_color.text = ls_ex_color
        text_view_interior_color.text = ls_in_color
        text_view_drive_type.text = ls_drivetype
        text_view_transmission.text = ls_trans
        text_view_body_style.text = ls_bodytype
        text_view_engine.text = ls_engine
        text_view_fuel.text = ls_fuel

        Glide.with(this)
            .load(ls_photo)
            .placeholder(R.drawable.no_image)
            .into(image_view_photo_large)

        button_call_dealer.setOnClickListener {
            val intentActivity = Intent(Intent.ACTION_CALL, Uri.parse("tel:$ls_call"))
            ContextCompat.startActivity(this, intentActivity, null)
        }
    }
}
package com.worldimage.carfax.data.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.worldimage.carfax.R
import com.worldimage.carfax.data.response.Listings
import com.worldimage.carfax.ui.DetailedActivity
import java.text.NumberFormat


class VehicleListAdapter: RecyclerView.Adapter<VehicleListAdapter.MyViewHolder>() {

    var vehicleList = listOf<Listings>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListAdapter.MyViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_list_item, parent, false)
        return MyViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: VehicleListAdapter.MyViewHolder, position: Int) {
        holder.bind(vehicleList[position])
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val listImage: ImageView = view.findViewById<ImageView>(R.id.listImage)
        private val listName: TextView = view.findViewById<TextView>(R.id.listName)
        private val listPrice: TextView = view.findViewById<TextView>(R.id.listPrice)
        private val listAddress: TextView = view.findViewById<TextView>(R.id.listAddress)
        private val listCallDealer: TextView = view.findViewById<TextView>(R.id.listCallDealer)

        @SuppressLint("SetTextI18n")
        fun bind(data: Listings){

            //click Listener
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailedActivity::class.java)
                intent.putExtra("ls_photo",data.images.medium[0])
                intent.putExtra("ls_year",data.year.toString())
                intent.putExtra("ls_make",data.make)
                intent.putExtra("ls_model",data.model)
                intent.putExtra("ls_trim",data.trim)
                intent.putExtra("ls_price",data.listPrice.toString())
                intent.putExtra("ls_mileage",data.mileage.toString())
                intent.putExtra("ls_location_city",data.dealer.city)
                intent.putExtra("ls_location_state",data.dealer.state)
                intent.putExtra("ls_call",data.dealer.phone)
                intent.putExtra("ls_bodytype",data.bodytype)
                intent.putExtra("ls_ex_color",data.exteriorColor)
                intent.putExtra("ls_in_color",data.interiorColor)
                intent.putExtra("ls_drivetype",data.drivetype)
                intent.putExtra("ls_trans",data.transmission)
                intent.putExtra("ls_engine",data.engine)
                intent.putExtra("ls_fuel",data.fuel)
                startActivity(itemView.context,intent,null)
                Log.e("Clicked", data.toString())
            }

            //list Name
            listName.text = "${data.year} ${data.make} ${data.model} ${data.trim}"

            //list Address
            listAddress.text = "${data.dealer.city}, ${data.dealer.state}"

            //list Price
            val price: Int = data.listPrice
            val numberFormat = NumberFormat.getNumberInstance()
            numberFormat.maximumFractionDigits = 0
            val convert = numberFormat.format(price)

            //list Mile
            val convMiles: Double = (data.mileage.toDouble()/1000)
            val convertedMiles:Double = String.format("%.1f", convMiles).toDouble()
            listPrice.text = "$ $convert | ${convertedMiles}k mi"

            //display Image
            val url = data.images.medium[0]
            Glide.with(listImage.context)
                .load(url)
                .placeholder(R.drawable.no_image)
                .into(listImage)

            listCallDealer.setOnClickListener {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${data.dealer.phone}"))
                startActivity(listCallDealer.context, intent, null)
            }


        }



    }

}



package com.worldimage.carfax.data.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.worldimage.carfax.R
import com.worldimage.carfax.data.response.Listings


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
            listName.text = "${data.year} ${data.make} ${data.model} ${data.trim}"
            listAddress.text = "${data.dealer.city}, ${data.dealer.state}"
            listPrice.text = "$ ${data.listPrice.toString()}"
            listCallDealer.text = data.dealer.phone

            val url = data.images.medium[0]

            Log.e("MainActivity", url)

            Glide.with(listImage.context)
                .load(url)
                .placeholder(R.drawable.no_image)
                .into(listImage);
        }
    }

}
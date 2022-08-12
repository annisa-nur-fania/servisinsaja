package com.servisinsaja.v2.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.servisinsaja.v2.Api.DataItem
import com.servisinsaja.v2.Form.FormTvActivity
import com.servisinsaja.v2.Haversine
import com.servisinsaja.v2.R
import java.text.DecimalFormat


class CardViewAdapter ( val listservis: List<DataItem?>?)
: RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return CardViewViewHolder(view)
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_jasa)
        var tvjarak: TextView = itemView.findViewById(R.id.tv_jarak)
        var tvalamat : TextView = itemView.findViewById(R.id.tv_alamat)

    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {



        holder.tvName.text = listservis?.get(position)?.namaBengkel
        holder.itemView.setOnClickListener {
            val name = listservis?.get(position)?.namaBengkel
            Toast.makeText(holder.itemView.context, "$name", Toast.LENGTH_SHORT).show()
        }
//        val service = listservis[position]
//        val (name, alamat,jarak) = listservis[position]
//
//        mengambil hasil rumus haversine
//        val jaraknya = FormTvActivity().jaraknya
//        Glide.with(holder.itemView.context)
//            .load(service.photo)
//            .apply(RequestOptions().override(350, 550))
//            .into(holder.imgPhoto)
//
//        holder.tvName.text = service.name
//        holder.tvalamat.text = service.alamat
//        holder.tvjarak.text = service.jarak + " KM"
//        //holder.tvjarak.text = DecimalFormat ("#.##").format(jaraknya) + " KM"
//        val mContext = holder
//            .itemView.context
//        holder.itemView.setOnClickListener {
//            //detail
//            val moveDetail = Intent(mContext, DetailTvActivity::class.java)
//            moveDetail.putExtra(DetailTvActivity.EXTRA_NAME, name)
//            moveDetail.putExtra(DetailTvActivity.EXTRA_JARAK, jarak)
//            moveDetail.putExtra(DetailTvActivity.EXTRA_ALAMAT, alamat)
//            moveDetail.putExtra(DetailTvActivity.EXTRA_PHOTO, service.photo)
//            mContext.startActivity(moveDetail)
//        }
    }


    override fun getItemCount(): Int {
        if (listservis != null){
            return  listservis.size

        }
        return  0
       // return listservis.size
    }
}
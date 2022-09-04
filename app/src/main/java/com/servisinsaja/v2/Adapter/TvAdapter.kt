package com.servisinsaja.v2.Adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.servisinsaja.v2.ApiPerbaikan.ResponsePerbaikanItem
import com.servisinsaja.v2.Detail.DetailTvActivity
import com.servisinsaja.v2.R
import com.servisinsaja.v2.databinding.ActivityDetailTvBinding
import kotlinx.android.synthetic.main.activity_form_tv.*
import kotlinx.android.synthetic.main.rv_radiobutton.view.*

class TvAdapter ( val listperbaikan: ArrayList<ResponsePerbaikanItem>)
    : RecyclerView.Adapter<TvAdapter.TvAdapterViewHolder>() {

    var listharga = 0
    var isSelected : Boolean = false

    private lateinit var binding: ActivityDetailTvBinding


    inner class TvAdapterViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkBox = itemView.cb_harga
        var itemharga = itemView.item_harga
        var jenis = itemView.tv_perbaikan

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_radiobutton, parent, false)
        return TvAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvAdapterViewHolder, position: Int) {

        var data_harga = listperbaikan.get(position).harga
        var data_jenis = listperbaikan.get(position).jenisPerbaikan
        holder.checkBox.text = data_harga
        holder.jenis.text = data_jenis


        holder.checkBox.setOnClickListener {

            if (holder.checkBox.isChecked){

            }else{

                
            }

//            total_harga = total_harga + 90
//            holder.jenis.text = total_harga.toString()
            //total_harga = Integer.parseInt(total_harga.toString().trim())+ Integer.parseInt(data_harga.trim())

            // get statusnya  =>  | checked  |  unchecked |

            // kalo dia checked
            // penambahan

            // kalo dia unchecked
            // pengurangan



        }
    }

    fun getItem() = listperbaikan
    override fun getItemCount(): Int = listperbaikan.size

}








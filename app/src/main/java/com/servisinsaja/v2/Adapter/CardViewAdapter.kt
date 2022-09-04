package com.servisinsaja.v2.Adapter



import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.servisinsaja.v2.Api.ResponseServisItem
import com.servisinsaja.v2.Detail.DetailTvActivity
import com.servisinsaja.v2.Haversine
import com.servisinsaja.v2.R
import com.servisinsaja.v2.Variable
import kotlinx.android.synthetic.main.item_cardview.view.*
import kotlinx.android.synthetic.main.item_cardview.view.tv_jarak
import java.text.DecimalFormat

class CardViewAdapter ( val listservis: ArrayList<ResponseServisItem>)
: RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return CardViewViewHolder(view)
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
//        var tvName: TextView = itemView.findViewById(R.id.tv_jasa)
//        var tvjarak: TextView = itemView.findViewById(R.id.tv_jarak)
//        var tvalamat : TextView = itemView.findViewById(R.id.tv_alamat)

        fun bind (servisResponse: ResponseServisItem) {
            with(itemView){
                val des1 = servisResponse.latitude.toDouble()
                val des2 = servisResponse.longtitude.toDouble()

                val lat1 = Variable.latitude
                val lat2 = Variable.longtitude
                val jaraknya: Double = Haversine().haversine(lat1, lat2, des1, des2)
                Toast.makeText(itemView.context, "lat : $lat1", Toast.LENGTH_SHORT).show()
                //holder.tvjarak.text = DecimalFormat ("#.##").format(jaraknya) + " KM"
                val name = " ${servisResponse.namaBengkel}\n"
                val alamat = " ${servisResponse.alamat}"
                val texttlp = " ${servisResponse.telp}"
                val image = "${servisResponse.gambar}"

                val jarak = DecimalFormat ("#.##").format(jaraknya) + " KM"
                tv_jasa.text = name
                tv_jarak.text = jarak
                tv_alamat.text = alamat

                val mContext = itemView.context
                itemView.setOnClickListener {
                //detail
                val moveDetail = Intent(mContext, DetailTvActivity::class.java)
                moveDetail.putExtra(DetailTvActivity.EXTRA_NAME, name)
                moveDetail.putExtra(DetailTvActivity.EXTRA_JARAK, jarak)
                moveDetail.putExtra(DetailTvActivity.EXTRA_ALAMAT, alamat)
                    moveDetail.putExtra(DetailTvActivity.EXTRA_ALAMAT, image)
                mContext.startActivity(moveDetail)
            }
            }
        }

    }


    override fun onBindViewHolder(holder: CardViewAdapter.CardViewViewHolder, position: Int) {

        holder.bind(listservis[position])
    }


    override fun getItemCount(): Int  = listservis.size
}
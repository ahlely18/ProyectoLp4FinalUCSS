package com.example.proyectofinal.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.proyectofinal.R
import com.example.proyectofinal.modelo.Ruta

class Adapta1(private val mlis:List<Ruta>, private val contexto: Context,
              private val itemClick:OnitemClick): RecyclerView.Adapter<Adapta1.ViewHolder>() {

    val urlpic = "http://192.168.1.20/AppViajes/images/"

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val pic1: ImageView = item.findViewById(R.id.imageRuta);
        val rutanom: TextView = item.findViewById(R.id.txtnombre)
    }

    interface OnitemClick {
        fun filaclick(art: Ruta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.vista1, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var a: Ruta= mlis.get(position)
        Glide.with(contexto)
          .load(urlpic + a.codRuta+".jpg")
          .error(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.pic1)

        holder.rutanom.setText(a.nome)
        holder.itemView.setOnClickListener { item ->
            itemClick.filaclick(a)
        }
    }

    override fun getItemCount(): Int {
        return mlis.size
    }
}

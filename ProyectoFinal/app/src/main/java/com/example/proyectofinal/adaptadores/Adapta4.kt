package com.example.proyectofinal.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.modelo.Viaje

class Adapta4(private val mlis:List<Viaje>, private val contexto: Context): RecyclerView.Adapter<Adapta4.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val cod: TextView = item.findViewById(R.id.txtcode)
        val ruta: TextView = item.findViewById(R.id.txtruta)
        val bus: TextView = item.findViewById(R.id.txtbus)
        val hora: TextView = item.findViewById(R.id.txthoras)
        val fecha: TextView = item.findViewById(R.id.txtfecha)
        val costo: TextView = item.findViewById(R.id.txtcosto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.vista4, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var a: Viaje= mlis.get(position)
        holder.cod.setText(a.nroviaje)
        holder.ruta.setText(a.codRuta)
        holder.bus.setText(""+a.nrobus)
        holder.hora.setText(a.hora)
        holder.fecha.setText(""+a.fecha)
        holder.costo.setText(""+a.costo)
    }

    override fun getItemCount(): Int {
        return mlis.size
    }
}

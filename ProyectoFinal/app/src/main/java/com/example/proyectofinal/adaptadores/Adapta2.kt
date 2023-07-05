package com.example.proyectofinal.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.modelo.Viaje

class Adapta2(private val mlis:List<Viaje>, private val contexto: Context,
              private val itemClick:OnitemClick): RecyclerView.Adapter<Adapta2.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val nroviaje: TextView = item.findViewById(R.id.txtnroviaje)
        val horario: TextView = item.findViewById(R.id.txthorario)
        val bus: TextView = item.findViewById(R.id.txt_bus)
        val costo: TextView = item.findViewById(R.id.txt_costo)
        val button: Button = item.findViewById(R.id.btnReservar)
    }

    interface OnitemClick {
        fun filaclick(art: Viaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.vista2, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var a: Viaje= mlis.get(position)
        holder.nroviaje.setText("Viaje ${a.nroviaje}")
        holder.bus.setText("Nro Bus: ${a.nrobus}")
        holder.horario.setText("Horario: ${a.fecha} ${a.hora}")
        holder.costo.setText("Costo: ${a.costo}")
        holder.button.setOnClickListener { item ->
            itemClick.filaclick(a)
        }
    }

    override fun getItemCount(): Int {
        return mlis.size
    }
}

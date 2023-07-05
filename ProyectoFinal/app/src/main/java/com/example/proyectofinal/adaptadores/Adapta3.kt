package com.example.proyectofinal.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.modelo.Pasajero

class Adapta3(private val mlis:List<Pasajero>, private val contexto: Context): RecyclerView.Adapter<Adapta3.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val bol: TextView = item.findViewById(R.id.txtcode)
        val nome: TextView = item.findViewById(R.id.txtruta)
        val asi: TextView = item.findViewById(R.id.txtbus)
        val tipo: TextView = item.findViewById(R.id.txtfecha)
        val pago: TextView = item.findViewById(R.id.txthoras)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.vista3, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var a: Pasajero= mlis.get(position)
        holder.bol.setText(a.boleto)
        holder.nome.setText(a.nombrePasajero)
        holder.asi.setText(""+a.num_asi)
        holder.tipo.setText(a.tipo)
        holder.pago.setText(""+a.pago)
    }

    override fun getItemCount(): Int {
        return mlis.size
    }
}

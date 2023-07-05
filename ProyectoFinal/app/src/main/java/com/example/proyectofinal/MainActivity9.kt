package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.adaptadores.Adapta3
import com.example.proyectofinal.adaptadores.Adapta4
import com.example.proyectofinal.databinding.ActivityMain9Binding
import com.example.proyectofinal.modelo.Pasajero
import com.example.proyectofinal.modelo.Viaje
import org.json.JSONException

class MainActivity9 : AppCompatActivity() {
    lateinit var bind: ActivityMain9Binding
    var cola: RequestQueue? = null
    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMain9Binding.inflate(layoutInflater)
        setContentView(bind.root)
        cola = Volley.newRequestQueue(this)

        bind.btnbuscar2.setOnClickListener {
            val nro:String = bind.edChofer.text.toString()
            consulta(nro)
        }

        bind.btnReturn9.setOnClickListener {
            var it: Intent = Intent(this, MainActivity7::class.java)
            startActivity(it)
        }
    }

    fun consulta(nro:String){
        var enlace = "$ruta?tag=consulta4&cod=$nro"
        val cad_soli = JsonObjectRequest(
            Request.Method.GET, enlace, null, { response ->
                try {
                    val vector = response.getJSONArray("dato")
                    Log.w("datos",vector.toString()) //
                    val lisw = arrayListOf<Viaje>()
                    for (f in 0 until vector.length()) {
                        val fila = vector.getJSONObject(f)
                        var via: Viaje = Viaje()
                        via.nroviaje = fila.getString("cod")
                        via.nrobus = fila.getString("bus")
                        via.codRuta = fila.getString("rutcod")
                        via.fecha = fila.getString("fecha")
                        via.hora = fila.getString("hora")
                        via.costo = fila.getDouble("costo")
                        lisw.add(via)
                    }

                    val ad = Adapta4(lisw,this)
                    bind.recy4.layoutManager = LinearLayoutManager(this)

                    bind.recy4.addItemDecoration(
                        DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
                    )
                    bind.recy4.adapter = ad
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Log.w("Red Error", "${error.message}")
            }
        )
        cola?.add(cad_soli)
    }
}
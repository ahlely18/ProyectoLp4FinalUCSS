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
import com.example.proyectofinal.databinding.ActivityMain8Binding
import com.example.proyectofinal.modelo.Pasajero
import org.json.JSONException

class MainActivity8 : AppCompatActivity() {
    lateinit var bind: ActivityMain8Binding
    var cola: RequestQueue? = null
    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMain8Binding.inflate(layoutInflater)
        setContentView(bind.root)
        cola = Volley.newRequestQueue(this)

        bind.btnBuscar1.setOnClickListener {
            val nro:String = bind.edViaje.text.toString()
            consulta(nro)
        }

        bind.btnReturn8.setOnClickListener {
            var it: Intent = Intent(this, MainActivity7::class.java)
            startActivity(it)
        }

    }

    fun consulta(nro:String){
        var enlace = "$ruta?tag=consulta3&cod=$nro"
        val cad_soli = JsonObjectRequest(
            Request.Method.GET, enlace, null, { response ->
                try {
                    val vector = response.getJSONArray("dato")
                    Log.w("datos",vector.toString()) //
                    val lisw = arrayListOf<Pasajero>()
                    for (f in 0 until vector.length()) {
                        val fila = vector.getJSONObject(f)
                        var pasa: Pasajero = Pasajero()
                        pasa.nombrePasajero = fila.getString("nombre")
                        pasa.boleto = fila.getString("cod")
                        pasa.num_asi = fila.getInt("nroasi")
                        pasa.tipo = fila.getString("tipo")
                        pasa.pago = fila.getDouble("pago")
                        lisw.add(pasa)
                    }

                    val ad = Adapta3(lisw,this)
                    bind.recy1.layoutManager = LinearLayoutManager(this)

                    bind.recy1.addItemDecoration(
                        DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
                    )
                    bind.recy1.adapter = ad
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
package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.adaptadores.Adapta2
import com.example.proyectofinal.databinding.ActivityMain4Binding
import com.example.proyectofinal.modelo.Viaje
import org.json.JSONException

class MainActivity4 : AppCompatActivity(), Adapta2.OnitemClick {
    lateinit var bind: ActivityMain4Binding
    var cola: RequestQueue? = null
    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    var nome:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(bind.root)
        cola= Volley.newRequestQueue(this);
        val codRuta = intent.getStringExtra("dato").toString()
        nome = intent.getStringExtra("nome").toString()
        carga(codRuta)
        bind.btnReturn4.setOnClickListener {
            var it: Intent = Intent(this, MainActivity2::class.java)
            it.putExtra("dato",codRuta)
            it.putExtra("nome",nome)
            startActivity(it)
        }

    }

    fun carga(codRuta:String){
        var enlace = "$ruta?tag=consulta2&cod=$codRuta"
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
                        via.codchofer = fila.getString("chofcod")
                        via.fecha = fila.getString("fecha")
                        via.hora = fila.getString("hora")
                        via.costo = fila.getDouble("costo")
                        lisw.add(via)
                    }

                    val ad = Adapta2(lisw,this,this)
                    bind.recy2.layoutManager = LinearLayoutManager(this)

                    bind.recy2.addItemDecoration(
                        DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
                    )
                    bind.recy2.adapter = ad
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

    override fun filaclick(art:Viaje) {
        var it: Intent = Intent(this, MainActivity5::class.java)
       it.putExtra("dato",art.nroviaje)
        it.putExtra("dato2", art.costo)
        Log.w("a ver costo",""+art.costo)
        it.putExtra("dato3",art.hora)
        it.putExtra("dato4",art.fecha)
        it.putExtra("nome",nome)
        it.putExtra("dato5",art.codRuta)
        startActivity(it)


    }
}


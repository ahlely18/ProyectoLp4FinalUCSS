package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.adaptadores.Adapta1
import com.example.proyectofinal.databinding.ActivityMain2Binding
import com.example.proyectofinal.modelo.Ruta
import com.example.proyectofinal.modelo.Usuario
import org.json.JSONException

class MainActivity2 : AppCompatActivity(), Adapta1.OnitemClick {
    lateinit var bind:ActivityMain2Binding
    var cola: RequestQueue? = null
    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    var nome:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(bind.root)
        cola= Volley.newRequestQueue(this);
        val art = intent.getSerializableExtra("dato") as Usuario
        nome = art.nome
        bind.txtUserName.setText("Bienvenido(a) ${art.nome}")
        carga()
        bind.btnLogOut.setOnClickListener {
            Toast.makeText(applicationContext,"Hasta pronto!", Toast.LENGTH_LONG).show()
            var it: Intent = Intent(this, MainActivity::class.java)
            startActivity(it)
        }
    }

    fun carga(){
        var enlace = "$ruta?tag=consulta1"
        val cad_soli = JsonObjectRequest(
            Request.Method.GET, enlace, null, { response ->
            try {
                val vector = response.getJSONArray("dato")
                Log.w("datos",vector.toString()) //
                val lisw = arrayListOf<Ruta>()
                for (f in 0 until vector.length()) {
                    val fila = vector.getJSONObject(f)
                    var ruta: Ruta = Ruta()
                    ruta.codRuta = fila.getString("cod")
                    ruta.nome = fila.getString("nome")
                    lisw.add(ruta)
                }
                val ad = Adapta1(lisw,this,this)
                bind.recy1.layoutManager = LinearLayoutManager(this)

                bind.recy1.addItemDecoration(
                    DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
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

    override fun filaclick(rut: Ruta) {
        var it: Intent = Intent(this, MainActivity4::class.java)
        it.putExtra("dato",rut.codRuta)
        it.putExtra("nome",nome)
        startActivity(it)
    }
}
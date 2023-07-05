package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.databinding.ActivityMain7Binding
import com.example.proyectofinal.modelo.Usuario

class MainActivity7 : AppCompatActivity() {
    lateinit var bind: ActivityMain7Binding
    var cola: RequestQueue? = null
    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    var nome:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMain7Binding.inflate(layoutInflater)
        setContentView(bind.root)
        cola = Volley.newRequestQueue(this)
        val art = intent.getSerializableExtra("dato") as Usuario
        nome = art.nome
        bind.txtBienven.setText("Bienvenido(a) ${art.nome}")
        bind.btnLogout2.setOnClickListener {
            Toast.makeText(applicationContext,"Hasta pronto!", Toast.LENGTH_LONG).show()
            var it: Intent = Intent(this, MainActivity::class.java)
            startActivity(it)
        }

        bind.cardPasajViaje.setOnClickListener{
            var it: Intent = Intent(this, MainActivity8::class.java)
            startActivity(it)
        }

        bind.cardChofViaje.setOnClickListener{
            var it: Intent = Intent(this, MainActivity9::class.java)
            startActivity(it)
        }

    }
}
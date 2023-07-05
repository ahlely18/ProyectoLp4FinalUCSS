package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.databinding.ActivityMain6Binding

class MainActivity6 : AppCompatActivity() {
    lateinit var bind: ActivityMain6Binding
    var cola: RequestQueue? = null
    var nome:String=""
    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMain6Binding.inflate(layoutInflater)
        setContentView(bind.root)
        cola= Volley.newRequestQueue(this);
        val hora = intent.getStringExtra("dato2").toString()
        val fecViaje = intent.getStringExtra("dato3").toString()
        val codViaje = intent.getStringExtra("dato1").toString()
        val costViaje = intent.getDoubleExtra("dato4",0.0)
        nome = intent.getStringExtra("dato5").toString()

        bind.txtBoleta.setText("Pasajero: ${nome}\nNro. viaje: ${codViaje}\nFecha del viaje: ${fecViaje}\nHora de salida del bus: ${hora}\nPago total: ${costViaje}")
        bind.btnCierre.setOnClickListener {
            Toast.makeText(applicationContext,"Hasta pronto!", Toast.LENGTH_LONG).show()
            var it: Intent = Intent(this, MainActivity::class.java)
            startActivity(it)
        }
    }
}
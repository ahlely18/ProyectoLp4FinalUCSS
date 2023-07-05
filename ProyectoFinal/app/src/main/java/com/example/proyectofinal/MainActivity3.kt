package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.databinding.ActivityMain3Binding
import org.json.JSONException

class MainActivity3 : AppCompatActivity() {
    lateinit var bind: ActivityMain3Binding
    var cola: RequestQueue? = null

    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(bind.root)
        cola = Volley.newRequestQueue(this)
        bind.btnReturn1.setOnClickListener {
            var it: Intent = Intent(this, MainActivity::class.java)
            startActivity(it)
        }
        bind.btnCreateAccount.setOnClickListener {
            val code = bind.edPassword.text.toString()
            val user = bind.edUser2.text.toString()
            val nome = bind.edNombreUser.text.toString()
            val correo = bind.edCorreo.text.toString()
            val tele = bind.edTelefono.text.toString()
            createAccount(code,user,nome,correo, tele)
        }
    }

    fun createAccount(code:String,user:String,nome:String,correo:String,tele:String){
        var enlace="$ruta?tag=create&code=$code&user=$user&nome=$nome&correo=$correo&tel=$tele"
        var strreq= JsonObjectRequest(Request.Method.GET,enlace,null, Response.Listener { response ->
            try{
                Toast.makeText(applicationContext,"Su cuenta se registró con éxito", Toast.LENGTH_LONG).show()
                var it: Intent = Intent(this, MainActivity::class.java)
                startActivity(it)
            }catch (e: JSONException) {
                e.printStackTrace() }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext,"error "+error.message, Toast.LENGTH_LONG).show()
        })
        cola?.add(strreq)
    }
}
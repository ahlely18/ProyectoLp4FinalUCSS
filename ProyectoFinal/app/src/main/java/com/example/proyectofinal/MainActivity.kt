package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.databinding.ActivityMainBinding
import com.example.proyectofinal.modelo.Usuario
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    lateinit var bind:ActivityMainBinding
    var cola: RequestQueue? = null
    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        cola = Volley.newRequestQueue(this)
        bind.btnLogin.setOnClickListener {
            val user = bind.edUser.text.toString()
            val pas = bind.edpass.text.toString()
            login(user,pas)
        }

        bind.btnCreate.setOnClickListener{
            var it: Intent = Intent(this, MainActivity3::class.java)
            startActivity(it)
        }
    }

    fun login(user:String, pas:String){
        var enlace=ruta+"?tag=login&user="+user+"&code="+pas;
        var strreq= JsonObjectRequest(Request.Method.GET,enlace,null, Response.Listener { response ->
            try{
                val vector=response.getJSONArray("dato")
                Log.w("datos",vector.toString())
                val lisw:ArrayList<Usuario> =ArrayList();
                var cod:String=""
                var ar= Usuario()
                for(f in 0 until vector.length()){

                    var fila=vector.getJSONObject(f)
                    ar.nome=fila.getString("nome");
                    ar.codUser=fila.getString("user");
                    ar.acceso=fila.getString("acceso");
                    lisw.add(ar)
                }

                if(lisw.isEmpty()){
                    Toast.makeText(applicationContext,"Datos erroneos", Toast.LENGTH_LONG).show()
                }else{
                    if(ar.acceso=="NO"){
                        var it: Intent = Intent(this, MainActivity2::class.java)
                        it.putExtra("dato",ar)
                        startActivity(it)
                    }else{
                        var it: Intent = Intent(this, MainActivity7::class.java)
                        it.putExtra("dato",ar)
                        startActivity(it)
                    }


                }
            }catch (e: JSONException) {
                e.printStackTrace() }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext,"error "+error.message, Toast.LENGTH_LONG).show()

        })
        cola?.add(strreq)
    }
}
package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyectofinal.databinding.ActivityMain5Binding
import org.json.JSONException

class MainActivity5 : AppCompatActivity() {
    lateinit var bind: ActivityMain5Binding
    var cola: RequestQueue? = null
    lateinit var listadisponibles:ArrayAdapter<String>

    val ruta = "http://192.168.163.20/AppViajes/Controla.php"
    //val ruta = "http://192.168.1.20/AppViajes/Controla.php"
    var nome:String=""
    var codRuta:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(bind.root)
        cola= Volley.newRequestQueue(this);
        val hora = intent.getStringExtra("dato3").toString()
        val fecViaje = intent.getStringExtra("dato4").toString()
        val codViaje = intent.getStringExtra("dato").toString()
        val costViaje = intent.getDoubleExtra("dato2",0.0)
        codRuta = intent.getStringExtra("dato5").toString()
        nome = intent.getStringExtra("nome").toString()
        Log.w("viaje",codViaje)
        Log.w("hora",hora)
        Log.w("fecha",fecViaje)
        Log.w("costo",""+costViaje)
        cargaSpinner(codViaje)
        bind.btnReservar.setOnClickListener {
            var spin = bind.spinner.selectedItemPosition+1
            var tipo:String=""
            var pago:Double = 0.0
            when(bind.rgp1.checkedRadioButtonId){
                R.id.rbEstudiante-> tipo="E"
                R.id.rbGeneral->tipo="G"
            }

            var cost:Double = costViaje.toDouble()
            Log.w("cost",""+cost)

            if(tipo=="E")  pago=cost-(cost*0.10)
            else pago = cost

            var enlace="$ruta?tag=adicion&cod=$codViaje&nome=$nome&tipo=$tipo&asi=$spin&pago=$pago"
            var nrobol:String=""
            var strreq= JsonObjectRequest(Request.Method.GET,enlace,null, Response.Listener { response ->
                try{
                    nrobol = response.getString("dato")
                    Toast.makeText(applicationContext,"Se reservó su boleto con exito", Toast.LENGTH_LONG).show()
                    var it: Intent = Intent(this, MainActivity6::class.java)
                    it.putExtra("dato1",codViaje)
                    it.putExtra("dato2",hora)
                    it.putExtra("dato3",fecViaje)
                    it.putExtra("dato4",pago)
                    it.putExtra("dato5",nome)
                    startActivity(it)
                }catch (e: JSONException) {
                    e.printStackTrace() }
            }, Response.ErrorListener { error ->
                Toast.makeText(applicationContext,"error "+error.message, Toast.LENGTH_LONG).show()
            })
            cola?.add(strreq)
        }

        bind.btnReturn5.setOnClickListener {
            var it: Intent = Intent(this, MainActivity4::class.java)
            it.putExtra("dato",codRuta)
            it.putExtra("nome",nome)
            startActivity(it)
        }
    }

    fun cargaSpinner(nrovia:String){
        var enlace = "$ruta?tag=consulta6&cod=$nrovia"
        val cad_soli = JsonObjectRequest(
            Request.Method.GET, enlace, null, { response ->
                try {
                    val vector = response.getJSONArray("dato")
                    Log.w("datos",vector.toString()) //
                    val lisasientosReservados = arrayListOf<Int>()

                    val asientos = arrayListOf<Int>()
                    for(i in 1 .. 20){
                        asientos.add(i)
                        //Log.w("nro",""+i)
                    }

                    for (f in 0 until vector.length()) {
                        val fila = vector.getJSONObject(f)
                        lisasientosReservados.add(fila.getInt("asi"))
                        Log.w("ocupado",""+fila.getInt("asi"))
                    }

                    var asiento_disponibles = arrayListOf<Int>()

                    // compara asientos
                    var cont=0
                    var valida=0
                    for(n in 0 until asientos.size){
                        for (k in 0 until lisasientosReservados.size){
                            if(asientos[n]==lisasientosReservados[k]){
                                valida=1
                               // Log.w("d","es igual ${asientos[n]}")
                                break
                            }else{
                                valida = 0
                               // Log.w("d","${asientos[n]} no es igual ${lisasientosReservados[k]}")
                            }

                        }

                        if(valida==0){
                            asiento_disponibles.add(asientos[n])
                            Log.w("dispo",""+asiento_disponibles[cont])
                            cont++

                        }
                    }

                    listadisponibles = ArrayAdapter<String>(this,R.layout.spinner_item)

                    listadisponibles.add("Selecciona")
                    for(a in 0  until asiento_disponibles.size){
                        Log.w("a verrr",""+asiento_disponibles.size)
                        listadisponibles.add(""+asiento_disponibles[a])
                    }

                   listadisponibles.setDropDownViewResource(R.layout.spinner_dropdown_item)
                   bind.spinner.adapter = listadisponibles
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

package com.example.mygymaplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConsultaUsuariosActivity : AppCompatActivity() {
    lateinit var db: BaseDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_usuarios)

        db = BaseDatos(this)
        val lista = db.listarUsuarios()
        val txt = findViewById<TextView>(R.id.txtListado)

        txt.text = if (lista.isNotEmpty()) {
            lista.joinToString("\n") { "• ${it.nombre} - ${it.clave}" }
        } else {
            "No hay usuarios registrados."
        }
    }
}

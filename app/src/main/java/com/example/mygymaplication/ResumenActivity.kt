package com.example.mygymaplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResumenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)

        // Obtener datos del intent
        val nombre = intent.getStringExtra("nombre")
        val genero = intent.getStringExtra("genero")
        val intereses = intent.getStringExtra("intereses")

        val resumenTexto = "Nombre: $nombre\nGénero: $genero\nIntereses: $intereses"

        val txtResumen = findViewById<TextView>(R.id.txtResumen)
        txtResumen.text = resumenTexto
    }

    fun volverAMain(view: View) {
        finish() // Cierra la pantalla actual y vuelve a la anterior
    }

}

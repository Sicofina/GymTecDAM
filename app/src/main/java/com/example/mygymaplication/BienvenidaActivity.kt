package com.example.mygymaplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BienvenidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        // Obtener el nombre del usuario (si se envió)
        val usuario = intent.getStringExtra("usuario")
        val textoBienvenida = findViewById<TextView>(R.id.textBienvenida)
        textoBienvenida.text = "Hola $usuario! Bienvenido a ClubDepo"
    }
}

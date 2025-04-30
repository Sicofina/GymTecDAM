package com.example.mygymaplication


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BienvenidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val usuario = intent.getStringExtra("usuario")
        val textoBienvenida = findViewById<TextView>(R.id.textBienvenida)
        textoBienvenida.text = "Hola $usuario! Bienvenido a ClubDepo"

        val btnNuevosClientes = findViewById<Button>(R.id.btnNuevosClientes)
        btnNuevosClientes.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}

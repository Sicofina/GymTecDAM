package com.example.mygymaplication

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PerfilClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_cliente)

        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvApellido = findViewById<TextView>(R.id.tvApellido)
        val tvDni = findViewById<TextView>(R.id.tvDni)
        val tvFechaNacimiento = findViewById<TextView>(R.id.tvFechaNacimiento)
        val tvTipoCliente = findViewById<TextView>(R.id.tvTipoCliente)
        val cbAptoFisico = findViewById<CheckBox>(R.id.cbAptoFisicoPerfil)

        val btnDarseDeBaja = findViewById<Button>(R.id.btnDarseDeBaja)
        val btnQuieroSerSocio = findViewById<Button>(R.id.btnQuieroSerSocio)

        // Obtener datos del intent
        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val dni = intent.getStringExtra("dni") ?: ""
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento") ?: ""
        val aptoFisico = intent.getBooleanExtra("aptoFisico", false)
        val tipoCliente = intent.getStringExtra("tipoCliente") ?: ""

        // Mostrar datos
        tvNombre.text = "Nombre: $nombre"
        tvApellido.text = "Apellido: $apellido"
        tvDni.text = "DNI: $dni"
        tvFechaNacimiento.text = "Fecha Nacimiento: $fechaNacimiento"
        tvTipoCliente.text = "Tipo: $tipoCliente"
        cbAptoFisico.isChecked = aptoFisico

        // Mostrar botón según tipo de cliente
        if (tipoCliente == "Socio") {
            btnDarseDeBaja.visibility = View.VISIBLE
            btnQuieroSerSocio.visibility = View.GONE
        } else {
            btnDarseDeBaja.visibility = View.GONE
            btnQuieroSerSocio.visibility = View.VISIBLE
        }
    }
}

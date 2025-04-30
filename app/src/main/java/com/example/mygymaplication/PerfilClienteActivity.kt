package com.example.mygymaplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

class PerfilClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_cliente)


        val nombre = intent.getStringExtra("nombre") ?: ""
        val apellido = intent.getStringExtra("apellido") ?: ""
        val dni = intent.getStringExtra("dni") ?: ""
        val fechaNacimiento = intent.getStringExtra("fechaNacimiento") ?: ""
        val tipoCliente = intent.getStringExtra("tipoCliente") ?: ""
        val aptoFisico = intent.getBooleanExtra("aptoFisico", false)

        findViewById<TextView>(R.id.tvNombre).text = "Nombre: $nombre"
        findViewById<TextView>(R.id.tvApellido).text = "Apellido: $apellido"
        findViewById<TextView>(R.id.tvDni).text = "DNI: $dni"
        findViewById<TextView>(R.id.tvFechaNacimiento).text = "Fecha Nac: $fechaNacimiento"
        findViewById<TextView>(R.id.tvTipoCliente).text = "Tipo: $tipoCliente"
        findViewById<CheckBox>(R.id.cbAptoFisicoPerfil).isChecked = aptoFisico

        // Mostrar botones según tipo de cliente
        findViewById<Button>(R.id.btnDarseDeBaja).visibility =
            if (tipoCliente == "Socio") Button.VISIBLE else Button.GONE

        findViewById<Button>(R.id.btnQuieroSerSocio).visibility =
            if (tipoCliente == "No Socio") Button.VISIBLE else Button.GONE

    }
}

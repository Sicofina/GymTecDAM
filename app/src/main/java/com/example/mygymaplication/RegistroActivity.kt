package com.example.mygymaplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etDni = findViewById<EditText>(R.id.etDni)
        val etFechaNacimiento = findViewById<EditText>(R.id.etFechaNacimiento)
        val cbAptoFisico = findViewById<CheckBox>(R.id.cbAptoFisico)
        val rbSocio = findViewById<RadioButton>(R.id.rbSocio)
        val rbNoSocio = findViewById<RadioButton>(R.id.rbNoSocio)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarCliente)

        val dbHelper = BaseDatos(this)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val dni = etDni.text.toString().trim()
            val fechaNacimiento = etFechaNacimiento.text.toString().trim()
            val aptoFisico = cbAptoFisico.isChecked
            val tipoCliente = if (rbSocio.isChecked) "Socio" else if (rbNoSocio.isChecked) "No Socio" else ""

            // Validación
            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || fechaNacimiento.isEmpty() || tipoCliente.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultado = dbHelper.insertarCliente(
                nombre,
                apellido,
                dni,
                fechaNacimiento,
                aptoFisico,
                tipoCliente
            )

            if (resultado != -1L) {
                Toast.makeText(this, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show()

                // Redirigir al perfil
                val intent = Intent(this, PerfilClienteActivity::class.java)
                intent.putExtra("nombre", nombre)
                intent.putExtra("apellido", apellido)
                intent.putExtra("dni", dni)
                intent.putExtra("fechaNacimiento", fechaNacimiento)
                intent.putExtra("aptoFisico", aptoFisico)
                intent.putExtra("tipoCliente", tipoCliente)
                startActivity(intent)

                finish()
            } else {
                Toast.makeText(this, "Error al registrar el cliente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

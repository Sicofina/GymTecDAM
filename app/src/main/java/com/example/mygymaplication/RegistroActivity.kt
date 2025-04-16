package com.example.mygymaplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var editNombre: EditText
    private lateinit var editClave: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        editNombre = findViewById(R.id.editNombre)
        editClave = findViewById(R.id.editClave)
    }

    fun guardarUsuario(view: View) {
        val nombre = editNombre.text.toString().trim()
        val clave = editClave.text.toString().trim()

        when {
            nombre.isEmpty() -> {
                Toast.makeText(this, "Ingresá un nombre", Toast.LENGTH_SHORT).show()
            }
            clave.length < 4 -> {
                Toast.makeText(this, "La clave debe tener al menos 4 caracteres", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val dbHelper = BaseDatos(this)
                val resultado = dbHelper.insertarUsuario(nombre, clave)

                if (resultado != -1L) {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                    editNombre.text.clear()
                    editClave.text.clear()
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

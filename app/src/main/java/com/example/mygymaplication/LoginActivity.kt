package com.example.mygymaplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editUsuario: EditText
    private lateinit var editClave: EditText
    private lateinit var db: BaseDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editUsuario = findViewById(R.id.editLoginUsuario)
        editClave = findViewById(R.id.editLoginClave)
        db = BaseDatos(this)
    }

    fun verificarLogin(view: View) {
        val usuarioIngresado = editUsuario.text.toString().trim()
        val claveIngresada = editClave.text.toString().trim()

        if (usuarioIngresado.isEmpty() || claveIngresada.isEmpty()) {
            Toast.makeText(this, "Campos vacíos", Toast.LENGTH_SHORT).show()
            return
        }

        val usuarios = db.listarUsuarios()
        val usuarioValido = usuarios.any { it.nombre == usuarioIngresado && it.clave == claveIngresada }

        if (usuarioValido) {
            Toast.makeText(this, "Bienvenido, $usuarioIngresado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.entrada, R.anim.salida)
            finish()
        } else {
            Toast.makeText(this, "Usuario o clave incorrecta", Toast.LENGTH_SHORT).show()
        }
    }
}

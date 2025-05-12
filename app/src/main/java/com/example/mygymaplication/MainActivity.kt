package com.example.mygymaplication

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editUsuario: EditText
    private lateinit var editClave: EditText
    private lateinit var cbMostrarClave: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editUsuario = findViewById(R.id.editLoginUsuario)
        editClave = findViewById(R.id.editLoginClave)
        cbMostrarClave = findViewById(R.id.cbMostrarClave)

        val btnIngresar = findViewById<Button>(R.id.btnIniciarSesion)
        btnIngresar.setOnClickListener { verificarLogin() }

        val btnRegistro = findViewById<Button>(R.id.btnIrRegistro)
        btnRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        cbMostrarClave.setOnCheckedChangeListener { _, isChecked ->
            val tipo = if (isChecked) android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            editClave.inputType = tipo
            editClave.setSelection(editClave.text.length)
        }
    }

    private fun verificarLogin() {
        val usuario = editUsuario.text.toString().trim()
        val clave = editClave.text.toString().trim()

        if (usuario.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val dbHelper = BaseDatos(this)
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM Usuario WHERE nombre = ? AND clave = ?",
            arrayOf(usuario, clave)
        )

        if (cursor.moveToFirst()) {
            Toast.makeText(this, "¡Bienvenido, $usuario!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, BienvenidaActivity::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }

        cursor.close()
        db.close()
    }
}

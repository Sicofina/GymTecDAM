package com.example.mygymaplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun mostrarResumen(view: View) {
        val nombre = findViewById<EditText>(R.id.editNombre).text.toString()

        val grupoGenero = findViewById<RadioGroup>(R.id.grupoGenero)
        val idSeleccionado = grupoGenero.checkedRadioButtonId
        val genero = if (idSeleccionado != -1) {
            findViewById<RadioButton>(idSeleccionado).text.toString()
        } else {
            "No especificado"
        }

        val intereses = mutableListOf<String>()
        if (findViewById<CheckBox>(R.id.cbDeporte).isChecked) intereses.add("Deporte")
        if (findViewById<CheckBox>(R.id.cbMusica).isChecked) intereses.add("Música")
        val interesesTexto = if (intereses.isNotEmpty()) intereses.joinToString(", ") else "Ninguno"

        val intent = Intent(this, ResumenActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("genero", genero)
        intent.putExtra("intereses", interesesTexto)
        startActivity(intent)
        overridePendingTransition(R.anim.entrada, R.anim.salida)
    }

    fun irARegistro(view: View) {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.entrada, R.anim.salida)
    }

    fun verUsuarios(view: View) {
        val intent = Intent(this, ConsultaUsuariosActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.entrada, R.anim.salida)
    }

    fun abrirModificarEliminar(view: View) {
        val intent = Intent(this, ModificarEliminarActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.entrada, R.anim.salida)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_opciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_registro -> {
                startActivity(Intent(this, RegistroActivity::class.java))
                overridePendingTransition(R.anim.entrada, R.anim.salida)
                true
            }
            R.id.menu_consulta -> {
                startActivity(Intent(this, ConsultaUsuariosActivity::class.java))
                overridePendingTransition(R.anim.entrada, R.anim.salida)
                true
            }
            R.id.menu_modificar -> {
                startActivity(Intent(this, ModificarEliminarActivity::class.java))
                overridePendingTransition(R.anim.entrada, R.anim.salida)
                true
            }
            R.id.menu_salir -> {
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

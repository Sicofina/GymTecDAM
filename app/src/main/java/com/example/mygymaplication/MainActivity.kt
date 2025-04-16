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

        // Obtener género
        val grupoGenero = findViewById<RadioGroup>(R.id.grupoGenero)
        val idSeleccionado = grupoGenero.checkedRadioButtonId
        val genero = if (idSeleccionado != -1) {
            findViewById<RadioButton>(idSeleccionado).text.toString()
        } else {
            "No especificado"
        }

        // Obtener intereses
        val intereses = mutableListOf<String>()
        if (findViewById<CheckBox>(R.id.cbDeporte).isChecked) intereses.add("Deporte")
        if (findViewById<CheckBox>(R.id.cbMusica).isChecked) intereses.add("Música")
        val interesesTexto = if (intereses.isNotEmpty()) intereses.joinToString(", ") else "Ninguno"

        // Lanzar ResumenActivity con los datos
        val intent = Intent(this, ResumenActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("genero", genero)
        intent.putExtra("intereses", interesesTexto)
        startActivity(intent)
    }

    fun irARegistro(view: View) {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    fun verUsuarios(view: View) {
        val intent = Intent(this, ConsultaUsuariosActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_opciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_registro -> {
                startActivity(Intent(this, RegistroActivity::class.java))
                true
            }
            R.id.menu_consulta -> {
                startActivity(Intent(this, ConsultaUsuariosActivity::class.java))
                true
            }
            R.id.menu_salir -> {
                finishAffinity() // Cierra la app
                true
            }
            R.id.menu_modificar -> {
                startActivity(Intent(this, ModificarEliminarActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun abrirModificarEliminar(view: View) {
        val intent = Intent(this, ModificarEliminarActivity::class.java)
        startActivity(intent)
    }



}

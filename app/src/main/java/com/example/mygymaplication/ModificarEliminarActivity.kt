package com.example.mygymaplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ModificarEliminarActivity : AppCompatActivity() {

    private lateinit var editBuscarNombre: EditText
    private lateinit var editNuevaClave: EditText
    private lateinit var db: BaseDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_eliminar)

        editBuscarNombre = findViewById(R.id.editBuscarNombre)
        editNuevaClave = findViewById(R.id.editNuevaClave)
        db = BaseDatos(this)
    }

    fun modificarUsuario(view: View) {
        val nombre = editBuscarNombre.text.toString().trim()
        val nuevaClave = editNuevaClave.text.toString().trim()

        if (nombre.isEmpty() || nuevaClave.isEmpty()) {
            Toast.makeText(this, "Completá ambos campos", Toast.LENGTH_SHORT).show()
            return
        }

        val dbWritable = db.writableDatabase
        val values = android.content.ContentValues().apply {
            put("clave", nuevaClave)
        }

        val filas = dbWritable.update("Usuario", values, "nombre = ?", arrayOf(nombre))
        dbWritable.close()

        if (filas > 0) {
            Toast.makeText(this, "Contraseña modificada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    fun eliminarUsuario(view: View) {
        val nombre = editBuscarNombre.text.toString().trim()

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingresá un nombre para eliminar", Toast.LENGTH_SHORT).show()
            return
        }

        val dbWritable = db.writableDatabase
        val filas = dbWritable.delete("Usuario", "nombre = ?", arrayOf(nombre))
        dbWritable.close()

        if (filas > 0) {
            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
            editBuscarNombre.text.clear()
            editNuevaClave.text.clear()
        } else {
            Toast.makeText(this, "No se encontró el usuario", Toast.LENGTH_SHORT).show()
        }
    }
}

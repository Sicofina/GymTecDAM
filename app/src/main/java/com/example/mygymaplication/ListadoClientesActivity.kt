package com.example.mygymaplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListadoClientesActivity : AppCompatActivity() {

    private lateinit var adaptador: ClienteAdapter
    private lateinit var listaClientes: List<Cliente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_clientes)

        val db = BaseDatos(this)
        listaClientes = db.obtenerTodosLosClientesOrdenados()

        adaptador = ClienteAdapter(this, listaClientes.toMutableList())
        val listView = findViewById<ListView>(R.id.listViewClientes)
        listView.adapter = adaptador

        val editBuscar = findViewById<EditText>(R.id.editBuscarCliente)
        editBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtrados = listaClientes.filter {
                    it.nombre.contains(s.toString(), ignoreCase = true)
                }
                adaptador.actualizarLista(filtrados)
            }
        })
    }
}

package com.example.mygymaplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class ClienteAdapter(private val contexto: Context, private var clientes: MutableList<Cliente>) : BaseAdapter() {

    fun actualizarLista(nuevaLista: List<Cliente>) {
        clientes.clear()
        clientes.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = clientes.size
    override fun getItem(position: Int): Any = clientes[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(contexto)
        val vista = inflater.inflate(R.layout.item_cliente, parent, false)

        val cliente = clientes[position]

        val tvNombre = vista.findViewById<TextView>(R.id.tvNombreItem)
        val tvDni = vista.findViewById<TextView>(R.id.tvDniItem)
        val tvTipo = vista.findViewById<TextView>(R.id.tvTipoItem)
        val btnVer = vista.findViewById<Button>(R.id.btnVerCliente)

        tvNombre.text = cliente.nombre
        tvDni.text = cliente.dni
        tvTipo.text = cliente.tipoCliente

        btnVer.setOnClickListener {
            val intent = Intent(contexto, PerfilClienteActivity::class.java).apply {
                putExtra("nombre", cliente.nombre)
                putExtra("apellido", cliente.apellido)
                putExtra("dni", cliente.dni)
                putExtra("fechaNacimiento", cliente.fechaNacimiento)
                putExtra("aptoFisico", cliente.aptoFisico)
                putExtra("tipoCliente", cliente.tipoCliente)
            }
            contexto.startActivity(intent)
        }

        return vista
    }
}

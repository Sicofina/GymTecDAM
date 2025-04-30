package com.example.mygymaplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context: Context) : SQLiteOpenHelper(context, "ClubDeportivo.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla de usuarios
        val sqlUsuario = """
            CREATE TABLE Usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT NOT NULL,
                clave TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(sqlUsuario)

        // Insertar usuario por defecto
        val valores = ContentValues().apply {
            put("usuario", "admin")
            put("clave", "admin")
        }
        db.insert("Usuario", null, valores)

        // Crear tabla de clientes
        val sqlCliente = """
            CREATE TABLE Cliente (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                apellido TEXT NOT NULL,
                dni TEXT NOT NULL,
                fechaNacimiento TEXT NOT NULL,
                aptoFisico INTEGER NOT NULL,
                tipoCliente TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(sqlCliente)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Usuario")
        db?.execSQL("DROP TABLE IF EXISTS Cliente")
        onCreate(db!!)
    }

    fun insertarUsuario(usuario: String, clave: String): Long {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("usuario", usuario)
            put("clave", clave)
        }
        return db.insert("Usuario", null, valores)
    }

    fun validarUsuario(usuario: String, clave: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM Usuario WHERE usuario = ? AND clave = ?"
        val cursor = db.rawQuery(query, arrayOf(usuario, clave))
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }

    fun obtenerTodosLosClientesOrdenados(): List<Cliente> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT nombre, apellido, dni, fechaNacimiento, aptoFisico, tipoCliente FROM Cliente ORDER BY nombre", null)

        val lista = mutableListOf<Cliente>()
        while (cursor.moveToNext()) {
            lista.add(
                Cliente(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 1,
                    cursor.getString(5)
                )
            )
        }
        cursor.close()
        return lista
    }

}

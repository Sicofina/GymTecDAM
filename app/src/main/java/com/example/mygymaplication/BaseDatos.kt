package com.example.mygymaplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context: Context) : SQLiteOpenHelper(context, "MiBase.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val crearTabla = """
            CREATE TABLE Usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                clave TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(crearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Usuario")
        onCreate(db)
    }

    fun insertarUsuario(nombre: String, clave: String): Long {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("clave", clave)
        }
        val id = db.insert("Usuario", null, valores)
        db.close()
        return id
    }

    fun listarUsuarios(): List<Usuario> {
        val lista = mutableListOf<Usuario>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT nombre, clave FROM Usuario", null)

        while (cursor.moveToNext()) {
            val nombre = cursor.getString(0)
            val clave = cursor.getString(1)
            lista.add(Usuario(nombre, clave))
        }

        cursor.close()
        db.close()
        return lista
    }

    data class Usuario(val nombre: String, val clave: String)
}


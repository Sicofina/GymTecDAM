package com.example.mygymaplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context: Context) : SQLiteOpenHelper(context, "ClubDeportivo.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla de usuarios
        val sql = """
            CREATE TABLE Usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT NOT NULL,
                clave TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(sql)

        // Insertar usuario por defecto: admin / admin
        val valores = ContentValues().apply {
            put("usuario", "admin")
            put("clave", "admin")
        }
        db.insert("Usuario", null, valores)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Usuario")
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
}

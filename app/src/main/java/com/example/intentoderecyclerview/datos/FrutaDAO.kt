package com.example.intentoderecyclerview.datos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.intentoderecyclerview.contracts.FrutaContract
import com.example.intentoderecyclerview.modelos.Fruta

class FrutaDAO {
    fun cargarLista(context: Context?):MutableList<Fruta> {
        lateinit var res:MutableList<Fruta>
        lateinit var c:Cursor
        try {
            //Para obtener acceso solo lectura
            val db = DBopenHelper.getInstance(context)!!.readableDatabase
            val columnas = arrayOf(FrutaContract.Companion.Entrada.COLUMNA_ID,
                FrutaContract.Companion.Entrada.COLUMNA_NOMBRE,
                FrutaContract.Companion.Entrada.COLUMNA_DESCRIPCION,
                FrutaContract.Companion.Entrada.COLUMNA_IMAGEN)
            c = db.query(FrutaContract.Companion.Entrada.NOMBRE_TABLA,
                columnas, null, null, null, null, null)
            res = mutableListOf()
            //Para leer los resultados del cursor y meterlos en la lista
            while (c.moveToNext()){
                val nueva = Fruta(c.getInt(0),c.getString(1),
                    c.getString(2),c.getInt(3))
                res.add(nueva)
            }
        } finally {
            c.close()
        }
        return res
    }

    fun actualizarBBDD(context: Context?, fruta: Fruta){
        val db = DBopenHelper.getInstance(context)!!.writableDatabase
        /*db.execSQL(
            "UPDATE frutas "
                    + "SET nombre='${fruta.nombre}' " +
                    "SET descripcion='${fruta.descripcion}'" +
                    "SET imagen='${fruta.imagen}'" +
                    "WHERE id=${fruta.id};"
        )
        */
        val values = ContentValues()
        values.put(FrutaContract.Companion.Entrada.COLUMNA_ID,fruta.id)
        values.put(FrutaContract.Companion.Entrada.COLUMNA_NOMBRE,fruta.nombre)
        values.put(FrutaContract.Companion.Entrada.COLUMNA_DESCRIPCION,fruta.descripcion)
        values.put(FrutaContract.Companion.Entrada.COLUMNA_IMAGEN,fruta.imagen)
        db.update(FrutaContract.Companion.Entrada.NOMBRE_TABLA,values,"id=?",arrayOf(fruta.id.toString()))
        db.close()
    }

}
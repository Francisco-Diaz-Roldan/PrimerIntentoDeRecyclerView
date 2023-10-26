package com.example.intentoderecyclerview.datos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.intentoderecyclerview.modelos.Fruta
import com.example.intentoderecyclerview.contracts.FrutaContract
import com.example.intentoderecyclerview.R

class DBopenHelper private constructor(context : Context?):
    SQLiteOpenHelper(context, FrutaContract.NOMBRE_BD, null, FrutaContract.VERSION){
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(
                "CREATE TABLE ${FrutaContract.Companion.Entrada.NOMBRE_TABLA}"
                        +"(${FrutaContract.Companion.Entrada.COLUMNA_ID} int NOT NULL"
                        + ",${FrutaContract.Companion.Entrada.COLUMNA_NOMBRE} NVARCHAR(20) NOT NULL"
                        + ",${FrutaContract.Companion.Entrada.COLUMNA_DESCRIPCION} NVARCHAR(40) NOT NULL"
                        + ",${FrutaContract.Companion.Entrada.COLUMNA_IMAGEN} int NOT NULL);"
            )
            // Insertar datos en la tabla
            inicializarBBDD(sqLiteDatabase)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i2: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${FrutaContract.Companion.Entrada.NOMBRE_TABLA};")
        onCreate(sqLiteDatabase)
    }

    private fun inicializarBBDD(db: SQLiteDatabase) {
        val lista = cargarFrutas()
        for (fruta in lista){
            db.execSQL(
                ("INSERT INTO ${FrutaContract.Companion.Entrada.NOMBRE_TABLA}"
                        +"(${FrutaContract.Companion.Entrada.COLUMNA_ID} int NOT NULL"
                        + ",${FrutaContract.Companion.Entrada.COLUMNA_NOMBRE} NVARCHAR(20) NOT NULL"
                        + ",${FrutaContract.Companion.Entrada.COLUMNA_DESCRIPCION} NVARCHAR(40) NOT NULL"
                        + ",${FrutaContract.Companion.Entrada.COLUMNA_IMAGEN} int NOT NULL);"
                )
            )
        }
    }

    private fun cargarFrutas(): MutableList<Fruta> {
        return mutableListOf(
            Fruta(1,"Fresas", "La fresa de Huelva, la mejor del mundo.", R.drawable.fresa),
            Fruta(2,"Manzanas", "Manzanas golden, pink lady...", R.drawable.manzana),
            Fruta(3,"Peras", "Peras blanquilla, conferencia, limonera...", R.drawable.pera),
            Fruta(4,"Bananas", "Plátano de Canarias, mejor que las bananas", R.drawable.platano),
            Fruta(5,"Cerezas", "Cerezas del Valle del Jerte", R.drawable.cereza),
            Fruta(6,"Naranjas", "Naranjas de Valencia", R.drawable.naranja),
            Fruta(7,"Frambuesas", "Las frambuesas aportan potasio, hierro y calcio...", R.drawable.frambuesa)
        )
    }

    companion object{
        private var dbOpen: DBopenHelper? = null
        fun getInstance(context: Context?): DBopenHelper? {
            if (dbOpen == null) dbOpen = DBopenHelper(context)
            return dbOpen
        }
    }

}
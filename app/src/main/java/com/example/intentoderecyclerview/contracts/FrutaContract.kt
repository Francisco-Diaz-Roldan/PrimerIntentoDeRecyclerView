package com.example.intentoderecyclerview.contracts

import android.provider.BaseColumns

class FrutaContract {
    companion object{
        const val NOMBRE_BD = "frutas"
        const val VERSION = 1
        class Entrada: BaseColumns{
            companion object{
                const val NOMBRE_TABLA = "frutas"
                const val COLUMNA_ID = "id"
                const val COLUMNA_NOMBRE = "nombre"
                const val COLUMNA_DESCRIPCION = "descripcion"
                const val COLUMNA_IMAGEN = "imagen"
            }
        }
    }
}

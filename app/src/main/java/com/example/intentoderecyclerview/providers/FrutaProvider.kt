package com.example.intentoderecyclerview.providers

import com.example.intentoderecyclerview.R
import com.example.intentoderecyclerview.modelos.Fruta

class FrutaProvider {
    companion object{
        val listaFrutas= mutableListOf(
            Fruta(5, "Fresas", "La fresa de Huelva, la mejor del mundo", R.drawable.fresa),
            Fruta(5, "Manzanas", "Manzanas golden, pink lady...", R.drawable.manzana),
            Fruta(5, "Peras", "Peras blanquilla, conferencia, limonera...", R.drawable.pera),
            Fruta(5, "Platanos", "Plátanos de Canarias, mejor que las bananas", R.drawable.platano),
            Fruta(5, "Cerezas", "Las más ricas del mundo", R.drawable.cereza),
            Fruta(5, "Naranjas", "Estas no son de valencia", R.drawable.naranja),
            Fruta(5, "Frambuesas", "Las frambuesas aportan potasio,calcio...", R.drawable.frambuesa)
        )
    }
}
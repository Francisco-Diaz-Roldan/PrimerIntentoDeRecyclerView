package com.example.intentoderecyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intentoderecyclerview.viewholders.FrutaViewHolder
import com.example.intentoderecyclerview.R
import com.example.intentoderecyclerview.modelos.Fruta

class FrutaAdapter (private val frutasLista:List<Fruta>,
                    private val  onClickListener:(Fruta)->Unit): RecyclerView.Adapter<FrutaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrutaViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return FrutaViewHolder(layoutInflater.inflate(R.layout.item_fruta, parent, false))
    }

     override fun onBindViewHolder(holder: FrutaViewHolder, position:Int){
         val item=frutasLista[position]
         holder.render(item, onClickListener)
     }

    override fun getItemCount(): Int {
        return frutasLista.size
    }
}

package com.example.intentoderecyclerview;


import android.view.ContextMenu
import android.view.View;
import android.view.View.OnClickListener
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.intentoderecyclerview.databinding.ItemFrutaBinding

class FrutaViewHolder(view:View): RecyclerView.ViewHolder(view) , View.OnCreateContextMenuListener {

    private val binding = ItemFrutaBinding.bind(view)
    private lateinit var fruta:Fruta

    fun render(item:Fruta, onClickListener: (Fruta)->Unit){
        fruta=item
        binding.tvFrutaNombre.text=item.nombre
        binding.tvDescripcion.text=item.descripcion
        Glide.with(binding.ivFruta.context).load(item.imagen).into(binding.ivFruta)

        //binding.ivFruta.setImageResource(item.imagen)
        itemView.setOnClickListener{
            onClickListener(item)
        }
        itemView.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(
        menu:ContextMenu?,
        v:View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        menu!!.setHeaderTitle(fruta.nombre)
        menu.add(this.adapterPosition,0,0,"Eliminar")
        menu.add(this.adapterPosition,1,1,"Editar")
    }
}

package com.example.intentoderecyclerview

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.intentoderecyclerview.FrutaProvider.Companion.listaFrutas
import com.example.intentoderecyclerview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FrutaAdapter
    private lateinit var layoutManager:LayoutManager

    private lateinit var intentLaunch:ActivityResultLauncher<Intent>
    private var nombre="Sin nombre"
    private var id:Int=0
    private var imagenFruta: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layoutManager=LinearLayoutManager(this)
        binding.rvFrutas.layoutManager=layoutManager
        adapter=FrutaAdapter(FrutaProvider.listaFrutas){fruta ->
            onItemSelected(fruta)
        }

        binding.rvFrutas.adapter=adapter
        binding.rvFrutas.setHasFixedSize(true)
        binding.rvFrutas.itemAnimator=DefaultItemAnimator()
        val decoration=DividerItemDecoration(this,RecyclerView.VERTICAL)
        binding.rvFrutas.addItemDecoration(decoration)

        //Para recibir las cosas
        intentLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            result ->
            if(result.resultCode == Activity.RESULT_OK){
                nombre = result.data?.extras?.getString("nombre").toString()//ActivityDos debe tener esta key para recibir los datos ("nombre")
                id = result.data?.extras?.getInt("idFruta")as Int
                FrutaProvider.listaFrutas[id].nombre = nombre
                adapter = FrutaAdapter(FrutaProvider.listaFrutas){
                    fruta -> onItemSelected(fruta)
                }
                adapter.notifyItemChanged(id)
                binding.rvFrutas.adapter = adapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addFruta -> {
                addFruta()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addFruta() {
        listaFrutas.add(listaFrutas.size,Fruta("Nueva fruta ${listaFrutas.size}",
            "Desconocida", R.drawable.ciruela))
    adapter.notifyItemInserted(listaFrutas.size)
    layoutManager.scrollToPosition(listaFrutas.size)
    }

    private fun onItemSelected(fruta: Fruta) {
        Toast.makeText(this,"Has pulsado sobre ${fruta.nombre}",Toast.LENGTH_SHORT).show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        lateinit var frutaAfectada:Fruta
        lateinit var miIntent:Intent
        frutaAfectada = listaFrutas[item.groupId]
        when (item.itemId){
            0 ->{
                val alert =
                    AlertDialog.Builder(this).setTitle("Eliminar ${frutaAfectada.nombre}")
                        .setMessage("¿Estás seguro de que quieres eliminar ${frutaAfectada.nombre}?"
                        )
                        .setNeutralButton("Cerrar", null).setPositiveButton(
                            "Aceptar"
                        ){_,_ ->
                            display("Se ha eliminado ${frutaAfectada.nombre}")
                            listaFrutas.removeAt(item.groupId)
                            adapter.notifyItemRemoved(item.groupId)
                            binding.rvFrutas.adapter = FrutaAdapter(listaFrutas){
                                fruta -> onItemSelected(fruta)
                            }
                        }.create()
                alert.show()
            }
            1 ->{
               miIntent = Intent(this, ActivityDos::class.java)
                miIntent.putExtra("nombreFruta", FrutaProvider.listaFrutas[item.groupId].nombre)//Esta es la key que uso para enviar objetos (""nombreFruta)
                miIntent.putExtra("idFruta", item.groupId)
                miIntent.putExtra("imagenFruta", FrutaProvider.listaFrutas[item.groupId].imagen)
                intentLaunch.launch(miIntent)
            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    private fun display(message: String) {
        Snackbar.make(binding.root,message,Snackbar.LENGTH_SHORT).show()
    }

}
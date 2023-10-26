package com.example.intentoderecyclerview.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.intentoderecyclerview.R

class ActivityDos: AppCompatActivity() {

    private lateinit var binding: ActivityDos  //Declaro una instancia de ActivityDos
    private var id : Int = 0                  //Declaro la variable id, que actúa como índice/identificador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos)
        val nombre = intent.getStringExtra("nombreFruta")
        val imagen = intent.getIntExtra("imagenFruta",0)
        id = intent.getIntExtra("idFruta",0)

        val nombreDos = findViewById<EditText>(R.id.etNombre)
        var imagenDos = findViewById<ImageView>(R.id.imgFrta)
        imagenDos.setImageResource(imagen)
        nombreDos.hint = nombre




        val btnDevolver = findViewById<Button>(R.id.btnDevolver)
        btnDevolver.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            val nombre = nombreDos.text.toString()
            intent.putExtra("nombre", nombre)
            intent.putExtra("imagenFruta", imagen)
            intent.putExtra("idFruta", id)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
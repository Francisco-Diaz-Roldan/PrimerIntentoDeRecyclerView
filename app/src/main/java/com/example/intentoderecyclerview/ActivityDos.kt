package com.example.intentoderecyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ActivityDos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos)
        val nombre = intent.getStringExtra("nombre")
        val imagen = intent.getIntExtra("imgFruta",R.drawable.ic_launcher_background)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        etNombre.hint = nombre
        val btnDevolver = findViewById<Button>(R.id.btnDevolver)

        btnDevolver.setOnClickListener{
            val intent = Intent()
            val nombre = etNombre.text.toString()

            intent.putExtra("nombre", nombre)
            intent.putExtra("imgFruta", imagen)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
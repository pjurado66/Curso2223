package com.pjurado.curso2223

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<EditText>(R.id.email)
        val pass = findViewById<EditText>(R.id.password)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val msg = "Se ha loggeado el usuario ${user.text} con la contraseña ${pass.text}"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

    }


}
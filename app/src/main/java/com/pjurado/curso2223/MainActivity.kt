package com.pjurado.curso2223

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pjurado.curso2223.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val msg = "Se ha loggeado el usuario ${binding.email.text} con la contraseña ${binding.password.text}"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

    }


}
package com.pjurado.curso2223

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myText = savedInstanceState?.getString("texto")

        if (myText != null){
            Toast.makeText(this, myText, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("texto", "Hola Pedro")
    }

}
package com.pjurado.curso2223.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pjurado.curso2223.App
import com.pjurado.curso2223.R
import com.pjurado.curso2223.ui.MainScreen.MainScreen
import com.pjurado.curso2223.ui.main.MainViewModel
import com.pjurado.curso2223.ui.ui.theme.Curso2223Theme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieDao = (application as App).db.movieDao()

        setContent {
            Curso2223Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val vm = viewModel { MainViewModel(resources.getString(R.string.api_key), movieDao = movieDao ) }
                    MainScreen(vm)
                }
            }
        }
    }


}


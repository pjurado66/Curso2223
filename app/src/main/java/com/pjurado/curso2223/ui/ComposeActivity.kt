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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pjurado.curso2223.App
import com.pjurado.curso2223.R
import com.pjurado.curso2223.ui.DetailScreen.DetailScreen
import com.pjurado.curso2223.ui.MainScreen.MainScreen
import com.pjurado.curso2223.ui.main.MainViewModel
import com.pjurado.curso2223.ui.ui.theme.Curso2223Theme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Curso2223Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Navigation()
                }
            }
        }
    }

}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val movieDao = app.db.movieDao()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            val vm = viewModel { MainViewModel(app.getString(R.string.api_key), movieDao = movieDao ) }
            MainScreen(vm, { navController.navigate("detail") })
        }
        composable("detail") {
            DetailScreen()
        }
    }
}

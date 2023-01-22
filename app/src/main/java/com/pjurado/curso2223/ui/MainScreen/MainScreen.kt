package com.pjurado.curso2223.ui.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.pjurado.curso2223.R
import com.pjurado.curso2223.ui.main.MainViewModel

@Composable
fun MainScreen(vm: MainViewModel, navega: () -> Unit) {
    val state = vm.state.observeAsState()
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) {padding ->
        LazyVerticalGrid(columns = GridCells.Adaptive(100.dp), modifier = Modifier.padding(4.dp)) {
            items(state.value?.movies ?: emptyList()){
                Column(modifier = Modifier
                    .padding(4.dp)
                    .clickable { navega() }
                ) {
                    Image(
                        painter = rememberImagePainter(it.urlImagen),
                        contentDescription = it.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = MaterialTheme.colors.primaryVariant)
                    ) {
                        Text(
                            text = it.title,
                            color = Color.White,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

        }


    }
}
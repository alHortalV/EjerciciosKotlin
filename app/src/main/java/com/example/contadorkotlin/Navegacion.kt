package com.example.contadorkotlin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

class Navegacion {
    @Composable
    fun navegacion() {
        val navController = rememberNavController()
        var contador by rememberSaveable { mutableIntStateOf(0) }
        NavHost(navController = navController, startDestination = ScreenNav) {
            composable<ScreenNav> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(onClick = { contador++ }) {
                        Text("Contador")
                    }
                    Text("$contador")
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        navController.navigate(SecondScreen(
                            numeros = contador,
                        ))
                    }) {
                        Text("Go to second screen")
                    }
                }
            }
            composable<SecondScreen> {
                val args = it.toRoute<SecondScreen>()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        navController.navigate(ScreenNav)
                    }) {
                        Text("Go Back")
                    }
                }
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("Número del contador: ${args.numeros}")
                }
            }
        }
    }
}

@Serializable
object ScreenNav
@Serializable
data class SecondScreen(val numeros : Int)
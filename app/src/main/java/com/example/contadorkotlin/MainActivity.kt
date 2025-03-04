package com.example.contadorkotlin

import com.example.contadorkotlin.movies.MovieViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import com.example.contadorkotlin.charts.OwnChart
import com.example.contadorkotlin.charts.OwnChartDetails

import com.example.contadorkotlin.ui.theme.ContadorKotlinTheme


class MainActivity : ComponentActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieViewModel.fetchMovies()

        setContent {
            ContadorKotlinTheme {
//                val movies by movieViewModel.movies.observeAsState(emptyList())
//                val error by movieViewModel.error.observeAsState()
//
//                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
//                    Column(
//                        modifier = Modifier
//                            .padding(paddingValues)
//                            .fillMaxSize(),
//                        verticalArrangement = Arrangement.Top,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        if (error != null) {
//                            Text(
//                                text = error!!,
//                                color = Color.Red,
//                                style = TextStyle(fontWeight = FontWeight.Bold)
//                            )
//                        } else {
//                            LazyColumn(modifier = Modifier.fillMaxWidth()) {
//                                items(movies) { movie ->
//                                    Column(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(16.dp),
//                                        horizontalAlignment = Alignment.CenterHorizontally
//                                    ) {
//                                        Text(
//                                            text = movie.title,
//                                            style = TextStyle(
//                                                color = Color.Red,
//                                                fontWeight = FontWeight.Bold,
//                                                fontSize = 18.sp
//                                            )
//                                        )
//                                        Image(
//                                            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/${movie.posterPath}"),
//                                            contentDescription = movie.title,
//                                            modifier = Modifier
//                                                .size(250.dp)
//                                                .padding(8.dp)
//                                        )
//                                        Text(
//                                            text = movie.overview,
//
//                                            style = TextStyle(fontSize = 14.sp),
//                                            modifier = Modifier.padding(8.dp)
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
                // ChartUsingVico().BasicLineChart()

                val data = listOf(
                    OwnChartDetails(5, "BMW", Color.Red),
                    OwnChartDetails(10, "AUDI", Color.Cyan),
                    OwnChartDetails(7, "FORD", Color.Magenta),
                    OwnChartDetails(1, "SEAT", Color.Green)
                )
                OwnChart().OwnChart(
                    data = data,
                    maxValue = 100
                )
            }
        }
    }
}

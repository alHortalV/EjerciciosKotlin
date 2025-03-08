package com.example.contadorkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.contadorkotlin.charts.OwnChart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WebSocketChartScreen()
                }
            }
        }
    }
}


@Composable
fun WebSocketChartScreen() {
    val ownChart = OwnChart()
    val websocketUrl = "ws://192.168.1.102:8080"
    OwnChartWrapper(ownChart = ownChart, websocketUrl = websocketUrl)
}


@Composable
fun OwnChartWrapper(ownChart: OwnChart, websocketUrl: String) {
    ownChart.OwnChart(url = websocketUrl, maxValue = 100)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        WebSocketChartScreen()
    }
}
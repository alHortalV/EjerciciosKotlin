package com.example.contadorkotlin.charts

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import com.example.contadorkotlin.charts.websocket.WebSocketManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.JsonElement

data class OwnChartDetails(
    val value: Int,
    val label: String,
    val color: Color
)

@kotlinx.serialization.Serializable
data class WebSocketData(
    val value: Int,
    val label: String,
    val color: String
)

class OwnChart {
    @SuppressLint("NotConstructor", "SuspiciousIndentation")
    @Composable
    fun OwnChart(url: String, maxValue: Int) {
        val webSocketManager = remember { WebSocketManager(url) }
        val scope = rememberCoroutineScope()
        var chartData by remember { mutableStateOf<List<OwnChartDetails>>(emptyList()) }

        LaunchedEffect(url) {
            webSocketManager.connect()

            scope.launch {
                webSocketManager.receivedData.collectLatest { data ->
                    data?.let {
                        try {
                            val jsonElement: JsonElement = Json.parseToJsonElement(it)
                            val webSocketData = Json.decodeFromJsonElement<WebSocketData>(jsonElement)
                            chartData = chartData + OwnChartDetails(
                                value = webSocketData.value,
                                label = webSocketData.label,
                                color = Color(android.graphics.Color.parseColor(webSocketData.color))
                            )
                        } catch (e: Exception) {
                            println(e.message)
                        }
                    }
                }
            }
        }

        DisposableEffect(webSocketManager) {
            onDispose {
                webSocketManager.disconnect()
            }
        }

        val textMeasurer = rememberTextMeasurer()
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            val barWidth = width / (chartData.size * 2)
            val bottomPadding = 30.sp.toPx()
            val availableHeight = height - bottomPadding

            val paint = Paint().apply {
                style = PaintingStyle.Stroke
            }

            var x = barWidth / 2

            chartData.forEach { item ->
                val barHeight = (item.value.toFloat() / maxValue.toFloat()) * availableHeight
                val topLeft = Offset(x = x, y = height - bottomPadding - barHeight)

                paint.color = item.color
                drawRect(
                    color = item.color,
                    topLeft = topLeft,
                    size = androidx.compose.ui.geometry.Size(barWidth, barHeight)
                )

                val textMeasure = textMeasurer.measure(
                    text = item.label,
                    style = TextStyle(fontSize = 12.sp, color = Color.Black)
                )
                val textWidth = textMeasure.size.width
                val textX = x + (barWidth - textWidth) / 2
                val textY = height - 20.sp.toPx()

                drawText(
                    textLayoutResult = textMeasure,
                    topLeft = Offset(textX, textY)
                )

                x += (barWidth + barWidth)
            }
        }
    }
}
package com.example.contadorkotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contadorkotlin.charts.ChartUsingVico

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataVisualizationPager() {
    val pages = listOf("Gráfico", "Configuración")
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PageIndicator(
            currentPage = pagerState.currentPage,
            pageNames = pages
        )

        VerticalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { page ->
            when (page) {
                0 -> VicoChartPage()
                1 -> SettingsPage()
            }
        }
    }
}

@Composable
fun PageIndicator(currentPage: Int, pageNames: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        pageNames.forEachIndexed { index, name ->
            val isSelected = index == currentPage

            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 14.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun VicoChartPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Gráfico con Vico",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(8.dp)
        ) {
            ChartUsingVico().BasicLineChart()
        }
    }
}

@Composable
fun SettingsPage() {
    val options = listOf("Conexión")
    val pagerState = rememberPagerState(pageCount = { options.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Configuración",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                SettingsContent()
            }
        }
    }
}

@Composable
fun SettingsContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Conectar")
        }
    }


}
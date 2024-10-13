package com.example.despionage

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.despionage.ui.theme.BoldText
import com.example.despionage.ui.theme.DespionageTheme
import com.example.despionage.ui.theme.HeaderText
import com.example.despionage.ui.theme.NormalText
import com.example.despionage.ui.theme.SubheaderText
import com.example.despionage.utility.Collector

class MainActivity : ComponentActivity() {
    val collector = Collector.getInstance(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DespionageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(
                        collector = collector,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Content(collector: Collector, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxHeight()
            .wrapContentHeight()
    ) {
        HeaderText("Despionage")
        SubheaderText("Android ${Build.VERSION.RELEASE} (${Build.VERSION.SECURITY_PATCH})")

        val apps = collector.getInstalledApps()
        val appsVisible = apps.filterNot { it.name.isNullOrEmpty() }

        TableView(
            data = listOf(
                TableRow("Brand", collector.getDeviceBrand()),
                TableRow("Model", collector.getDeviceModel()),
                TableRow("Name", collector.getDeviceName()),
                TableRow("Apps installed", apps.size.toString()),
                TableRow("Hidden", (apps.size - appsVisible.size).toString()),
                TableRow("Visible:", appsVisible.size.toString()),
                TableRow("", appsVisible
                    .map { it.name.replace(Regex("(?<!\\.)_?App(lication)?"), "") }
                    .map { if (it.length <= 32) it else it.replaceRange(0, it.length - 29, "...") }
                    .joinToString("\n")
                )
            )
        )
    }
}

data class TableRow(val name: String, val value: String)

@Composable
fun TableView(data: List<TableRow>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(all = 32.dp)
            .wrapContentWidth()
            .fillMaxHeight(0.9f)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
    ) {
        items(data.size) { i ->
            val rowdata = data[i]

            val color =
                if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .background(color = color)
            ) {
                BoldText(
                    rowdata.name,
                    modifier = modifier
                        .padding(16.dp)
                )
                NormalText(
                    text = rowdata.value,
                    TextAlign.End,
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
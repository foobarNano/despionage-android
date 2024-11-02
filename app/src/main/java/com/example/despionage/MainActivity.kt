package com.example.despionage

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.despionage.ui.theme.DespionageTheme
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
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Content(collector: Collector, modifier: Modifier = Modifier) {

    val items: List<@Composable () -> Unit> = listOf(
        {
            Column (
                modifier = modifier
                    .padding(32.dp, 48.dp)
                    .wrapContentHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_full),
                    contentDescription = "Despionage",
                    modifier = Modifier.padding(0.dp, 32.dp)
                )
                SubheaderText("Android ${Build.VERSION.RELEASE} (${Build.VERSION.SECURITY_PATCH})")
            }
        },
        {
            Fact()
        },
        {
            Fact(flipped = true)
        },
        {
            Fact()
        },
        {
            Fact(flipped = true)
        },
        {
            Fact()
        }
    )

    LazyColumn() {

        itemsIndexed(items) { index, itemContent ->

            itemContent()
        }
    }
}
package com.example.despionage.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalText(
    text: String,
    textAlign: TextAlign = TextAlign.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = TextSize.sp,
        fontWeight = FontWeight.Normal,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun BoldText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = TextSize.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun SubheaderText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.tertiary,
        fontSize = SubheaderSize.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth()
    )
}

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = HeaderSize.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(vertical = TextSize.dp)
            .fillMaxWidth()
            .wrapContentWidth()
    )
}
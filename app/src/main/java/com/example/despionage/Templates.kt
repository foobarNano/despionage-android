package com.example.despionage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.despionage.ui.theme.HeaderSize
import com.example.despionage.ui.theme.SubheaderSize
import com.example.despionage.ui.theme.TextSize

@Composable
fun NormalText(
    text: String,
    textAlign: TextAlign = TextAlign.Justify,
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
    textAlign: TextAlign = TextAlign.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = TextSize.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun ItalicText(
    text: String,
    textAlign: TextAlign = TextAlign.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = TextSize.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        textAlign = textAlign,
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
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = HeaderSize.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        lineHeight = HeaderSize.times(1.2f).sp,
        maxLines = maxLines,
        modifier = modifier
    )
}

@Composable
fun Fact(
    title: String = "Example vulnerability",
    description: String = "Consequatur quod tenetur blanditiis voluptatibus iste magni ut dolorem. Illo debitis perferendis vero maiores voluptatem.",
    punchline: String = "Very bad stuff!",
    icon: Painter = painterResource(id = R.drawable.question),
    iconDesc: String = icon.toString(),
    flipped: Boolean = false
) {
    val modifier = Modifier

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(24.dp, 32.dp)
        ) {
            if (!flipped) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = icon,
                        contentDescription = iconDesc,
                        modifier = modifier
                            .size(80.dp, 80.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    HeaderText(
                        text = title,
                        textAlign = TextAlign.End,
                        maxLines = 2,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            else {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    HeaderText(
                        text = title,
                        textAlign = TextAlign.Start,
                        maxLines = 2,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .fillMaxWidth(0.65f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Image(
                        painter = icon,
                        contentDescription = iconDesc,
                        modifier = modifier
                            .size(80.dp, 80.dp)
                    )
                }
            }

            NormalText(
                text = description,
                modifier = modifier
                    .fillMaxWidth()
            )

            SubheaderText(
                text = punchline
            )
        }
    }
}
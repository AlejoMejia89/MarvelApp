package com.alejandromejia.marvelapp.ui.favorites

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandromejia.marvelapp.domain.models.characters.DFavorites
import com.alejandromejia.marvelapp.ui.components.ImageApp
import com.alejandromejia.marvelapp.utils.NO_CONTENT_DESCRIPTION

@Composable
fun ItemsFavorite(item: DFavorites) {

    Card(
        border = BorderStroke(1.dp, Color.White), modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.Black
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    Color.Black
                )
        ) {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .padding(5.dp)
                    .clip(shape = RoundedCornerShape(100))
            ) {
                ImageApp(imageUrl = item.thumbnail, isFromMainView = true)
            }
            Column(
                modifier = Modifier.padding(
                    bottom = 10.dp,
                    start = 10.dp,
                    top = 10.dp,
                    end = 15.dp
                )
            ) {
                Text(
                    text = item.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 16.sp,
                    color = Color.White
                )
                Text(
                    text = item.description.ifEmpty { NO_CONTENT_DESCRIPTION },
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
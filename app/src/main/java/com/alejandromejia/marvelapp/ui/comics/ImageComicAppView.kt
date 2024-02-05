package com.alejandromejia.marvelapp.ui.comics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageComicAppView(
    path: String,
    startY: Float,
    endY: Float,
    startColor: Color,
    endColor: Color,
    onItemSelected: () -> Unit
) {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(startColor, endColor),
        startY = startY,
        endY = endY
    )

    Box(modifier = Modifier.height(460.dp)) {
        if (isValidUrl(path) && path.isNotEmpty()) {
            GlideImage(
                model = path,
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(brush = gradientBrush)
                    .clickable { onItemSelected() }
                    .align(Alignment.Center)
            )

        } else {
            Image(
                painter = painterResource(id = R.drawable.background_img_default),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(brush = gradientBrush)
            )
        }
    }


}

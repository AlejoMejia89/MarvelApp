package com.alejandromejia.marvelapp.ui.comics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.delay
import java.net.URL

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageComicApp(
    imageUrl: String,
    isFromMainView: Boolean
) {

    var updatedPath by remember { mutableStateOf(imageUrl) }

    val modifier =
        if (isFromMainView)
            Modifier.fillMaxSize()
        else Modifier.size(170.dp)

    Box(
        modifier = modifier
    ) {
        if (isValidUrl(imageUrl) && imageUrl.isNotEmpty()) {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )

            LaunchedEffect(imageUrl) {
                updatedPath = imageUrl
            }

            LaunchedEffect(Unit) {
                while (true) {
                    updatedPath = imageUrl
                    delay(1000)
                }
            }

        } else {
            Image(
                painter = painterResource(id = R.drawable.background_img_default),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))

            )
        }
    }

}

fun isValidUrl(urlString: String): Boolean {
    return try {
        val url = URL(urlString)
        url.toURI()
        true
    } catch (e: Exception) {
        false
    }
}
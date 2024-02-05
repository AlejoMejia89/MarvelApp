package com.alejandromejia.marvelapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.R
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView

@Composable
fun ProgressBarApp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 16.dp, bottom = 40.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(90.dp),
            color = Color.Red
        )
        Image(
            painter = painterResource(id = R.drawable.ic_marvel),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Red),
            modifier = Modifier
                .align(Alignment.Center)
                .size(70.dp)
                .background(Color.White, RoundedCornerShape(60.dp)),
        )
    }
}
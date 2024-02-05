package com.alejandromejia.marvelapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alejandromejia.marvelapp.R

@Composable
fun HeaderAppView() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .weight(1f)
                .height(3.dp)
                .align(Alignment.CenterVertically)
        )
        Icon(
            painterResource(id = R.drawable.logo_marvel),
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.65f)
                .background(Color.White)
                .wrapContentWidth()
        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(3.dp)
                .align(Alignment.CenterVertically)
                .weight(1f)
        )

    }
}
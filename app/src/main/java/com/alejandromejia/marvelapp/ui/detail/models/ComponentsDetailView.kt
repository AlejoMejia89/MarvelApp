package com.alejandromejia.marvelapp.ui.detail.models

import androidx.compose.ui.graphics.Color

data class ComponentsDetailView(
    val view: String,
    val title: String,
    val background: Color,
    val onBackView: Boolean,
)
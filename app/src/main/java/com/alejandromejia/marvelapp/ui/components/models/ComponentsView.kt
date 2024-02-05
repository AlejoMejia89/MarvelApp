package com.alejandromejia.marvelapp.ui.components.models

import androidx.compose.ui.graphics.Color

data class ComponentsView(
    val view: String,
    val title: String,
    val background: Color,
    val onBackView: Boolean,
)

package com.alejandromejia.marvelapp.utils

import com.alejandromejia.marvelapp.R

fun Int.isZero(): Boolean = this == ZERO

fun Int.isNotZero(): Boolean = this != ZERO

fun Int?.orZero(): Int = this ?: ZERO

fun Int?.orDefaultString(): Int = this ?: R.string.error_message

fun Int.isPositive(): Boolean = this >= 0
package com.alejandromejia.marvelapp.utils

fun Long.isZero(): Boolean = this == ZERO_LONG

fun Long.isNotZero(): Boolean = this != ZERO_LONG

fun Long?.orZero(): Long = this ?: ZERO_LONG

fun Long.isPositive(): Boolean = this >= ZERO_LONG
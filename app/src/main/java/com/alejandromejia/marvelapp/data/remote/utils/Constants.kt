package com.alejandromejia.marvelapp.data.remote.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object {

        const val BASE_URL = "https://gateway.marvel.com"
        private val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        private const val API_KEY = "92e4ed19f6564b270a7f48b3a9ac93cc"
        private const val PRIVATE_KEY = "0958e6eceea611a85e0c0c5eb987f9d1b8b16c2b"
        const val limit = "20"
        private fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        val commonQueryParams = mapOf(
            "apikey" to API_KEY,
            "ts" to timeStamp,
            "hash" to hash()
        )
    }
}
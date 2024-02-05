package com.alejandromejia.marvelapp.data.remote.network

sealed class Failure {
    object NetworkConnection : Failure()
    object DataError : Failure()
    object ServerError : Failure()

    data class FeatureFailure(
        val errorBody: String
    ) : Failure()
}
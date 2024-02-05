package com.alejandromejia.marvelapp.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoritesMarvelEntity")
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "thumbnail") val thumbnail: String? = null
)
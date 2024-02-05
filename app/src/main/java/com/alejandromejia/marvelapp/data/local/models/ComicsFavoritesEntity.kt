package com.alejandromejia.marvelapp.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoritesComicsEntity")
data class ComicsFavoritesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "name") val title: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "thumbnail") val thumbnail: String? = null,
    @ColumnInfo(name = "format") val format: String? = null,
    @ColumnInfo(name = "pageCount") val pageCount: Int? = null
)

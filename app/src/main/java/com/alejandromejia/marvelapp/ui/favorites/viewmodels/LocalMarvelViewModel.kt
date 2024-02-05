package com.alejandromejia.marvelapp.ui.favorites.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.domain.usecases.local.GetFavoritesUseCase
import com.alejandromejia.marvelapp.domain.usecases.local.RemoveFavoriteUseCase
import com.alejandromejia.marvelapp.domain.usecases.local.SaveFavoriteCharacterUseCase
import com.alejandromejia.marvelapp.ui.favorites.models.FavoritesView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalMarvelViewModel @Inject constructor(
    private val saveFavoriteCharacterUseCase: SaveFavoriteCharacterUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _favoriteCharacters: MutableLiveData<FavoritesView> =
        MutableLiveData<FavoritesView>()
    val favoriteCharacters: LiveData<FavoritesView> get() = _favoriteCharacters


    fun saveFavorite(model: DResult) {
        viewModelScope.launch {
            saveFavoriteCharacterUseCase(model)
        }
    }

    fun removeFavorite(model: DResult) {
        viewModelScope.launch {
            removeFavoriteUseCase(model)
        }
    }

    fun getFavoritesCharacters() {
        viewModelScope.launch {
            _favoriteCharacters.value = FavoritesView(
                charactersList = getFavoritesUseCase(),
                loading = false
            )
        }
    }
}
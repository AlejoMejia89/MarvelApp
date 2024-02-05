package com.alejandromejia.marvelapp.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.marvelapp.domain.models.search.DRecentSearch
import com.alejandromejia.marvelapp.domain.usecases.local.GetRecentSearchUseCase
import com.alejandromejia.marvelapp.domain.usecases.local.RemoveRecentSearchUseCase
import com.alejandromejia.marvelapp.domain.usecases.local.SaveRecentSearchUseCase
import com.alejandromejia.marvelapp.ui.search.model.RecentSearchView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalRecentSearchViewModel @Inject constructor(
    private val saveRecentSearchUseCase: SaveRecentSearchUseCase,
    private val removeRecentSearchUseCase: RemoveRecentSearchUseCase,
    private val getRecentSearchUseCase: GetRecentSearchUseCase
) : ViewModel() {

    private val _recentSearch: MutableLiveData<RecentSearchView> =
        MutableLiveData<RecentSearchView>()
    val recentSearch: LiveData<RecentSearchView> get() = _recentSearch


    fun saveFavoriteComics(model: DRecentSearch) {
        viewModelScope.launch {
            saveRecentSearchUseCase(model)
        }
    }

    fun removeFavorite(model: DRecentSearch) {
        viewModelScope.launch {
            removeRecentSearchUseCase(model)
        }
    }

    fun getFavoritesComics() {
        viewModelScope.launch {
            _recentSearch.value = RecentSearchView(
                recentSearch = getRecentSearchUseCase(),
                loading = false
            )
        }
    }
}
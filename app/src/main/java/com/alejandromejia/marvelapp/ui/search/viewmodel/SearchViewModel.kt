package com.alejandromejia.marvelapp.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandromejia.marvelapp.data.remote.network.Failure
import com.alejandromejia.marvelapp.domain.models.characters.DCharacters
import com.alejandromejia.marvelapp.domain.models.characters.DResult
import com.alejandromejia.marvelapp.domain.usecases.local.GetFavoritesUseCase
import com.alejandromejia.marvelapp.domain.usecases.remote.SearchUseCase
import com.alejandromejia.marvelapp.ui.search.model.SearchItemsView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchItemsUseCase: SearchUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _searchItemsView =
        MutableLiveData<SearchItemsView>()
    val searchItemsView: LiveData<SearchItemsView> get() = _searchItemsView

    private val _focused: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>()
    val focused: LiveData<Boolean> get() = _focused

    private val _query: MutableLiveData<String> =
        MutableLiveData<String>()
    val query: LiveData<String> get() = _query

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun isFocused(focused: Boolean) {
        _focused.value = focused
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    fun getItems(text: String) {
        _searchItemsView.value = SearchItemsView(loading = true)
        viewModelScope.launch {
            searchItemsUseCase
                .invoke(text)
                .fold(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(results: DCharacters) {
        val result = validateFavorites(results)
        _searchItemsView.value = SearchItemsView(
            charactersList = result,
            loading = false
        )
    }

    private fun handleFailure(failure: Failure) {
        _searchItemsView.value =
            SearchItemsView(errorMessage = failure.toString())
        _errorMessage.value = failure.toString()
    }

    private fun validateFavorites(character: DCharacters): List<DResult> {
        val favoriteIds = getFavoritesUseCase().map { it.id }

        return character.data.results.map { result ->
            result.copy(isFavorite = favoriteIds.contains(result.id))
        }
    }
}
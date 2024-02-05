package com.alejandromejia.marvelapp.ui.components.models

import com.alejandromejia.marvelapp.ui.comics.viewmodels.ComicViewModel
import com.alejandromejia.marvelapp.ui.components.viewmodels.MainViewModel
import com.alejandromejia.marvelapp.ui.detail.viewmodels.CharacterDetailViewModel
import com.alejandromejia.marvelapp.ui.favorites.viewmodels.LocalComicsViewModel
import com.alejandromejia.marvelapp.ui.favorites.viewmodels.LocalMarvelViewModel
import com.alejandromejia.marvelapp.ui.home.viemodels.CharactersViewModel
import com.alejandromejia.marvelapp.ui.search.viewmodel.LocalRecentSearchViewModel
import com.alejandromejia.marvelapp.ui.search.viewmodel.SearchViewModel

data class ViewModels(
    val charactersViewModel: CharactersViewModel,
    val mainViewModel: MainViewModel,
    val searchViewModel: SearchViewModel,
    val localMarvelViewModel: LocalMarvelViewModel,
    val characterDetailViewModel: CharacterDetailViewModel,
    val comicViewModel: ComicViewModel,
    val localComicsViewModel: LocalComicsViewModel,
    val localRecentSearchViewModel: LocalRecentSearchViewModel
)
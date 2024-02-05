package com.alejandromejia.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alejandromejia.marvelapp.domain.navigation.Routes
import com.alejandromejia.marvelapp.ui.comics.viewmodels.ComicViewModel
import com.alejandromejia.marvelapp.ui.components.ScaffoldMainView
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.ui.components.viewmodels.MainViewModel
import com.alejandromejia.marvelapp.ui.detail.ScaffoldDetailView
import com.alejandromejia.marvelapp.ui.detail.viewmodels.CharacterDetailViewModel
import com.alejandromejia.marvelapp.ui.favorites.viewmodels.LocalComicsViewModel
import com.alejandromejia.marvelapp.ui.favorites.viewmodels.LocalMarvelViewModel
import com.alejandromejia.marvelapp.ui.home.viemodels.CharactersViewModel
import com.alejandromejia.marvelapp.ui.search.viewmodel.LocalRecentSearchViewModel
import com.alejandromejia.marvelapp.ui.search.viewmodel.SearchViewModel
import com.alejandromejia.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var charactersViewModel: CharactersViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var localMarvelViewModel: LocalMarvelViewModel
    private lateinit var characterDetailViewModel: CharacterDetailViewModel
    private lateinit var comicViewModel: ComicViewModel
    private lateinit var localComicsViewModel: LocalComicsViewModel
    private lateinit var localRecentSearchViewModel: LocalRecentSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModels()
        setContent {
            MarvelAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    val viewModels = createViewModels()

                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.ScaffoldMainView.route
                    ) {
                        composable(route = Routes.ScaffoldMainView.route) {
                            ScaffoldMainView(
                                viewModels = viewModels,
                                navigationController = navigationController
                            )
                        }
                        composable(route = Routes.ScaffoldDetailView.route) {
                            ScaffoldDetailView(
                                viewModels = viewModels,
                                navigationController = navigationController
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModels() {
        mainViewModel = viewModels<MainViewModel>().value
        charactersViewModel = viewModels<CharactersViewModel>().value
        searchViewModel = viewModels<SearchViewModel>().value
        localMarvelViewModel = viewModels<LocalMarvelViewModel>().value
        characterDetailViewModel = viewModels<CharacterDetailViewModel>().value
        comicViewModel = viewModels<ComicViewModel>().value
        localComicsViewModel = viewModels<LocalComicsViewModel>().value
        localRecentSearchViewModel = viewModels<LocalRecentSearchViewModel>().value
    }

    private fun createViewModels(): ViewModels {
        return ViewModels(
            charactersViewModel,
            mainViewModel,
            searchViewModel,
            localMarvelViewModel,
            characterDetailViewModel,
            comicViewModel,
            localComicsViewModel,
            localRecentSearchViewModel
        )
    }
}

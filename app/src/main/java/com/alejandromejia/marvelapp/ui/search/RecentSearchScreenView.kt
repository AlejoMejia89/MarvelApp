package com.alejandromejia.marvelapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandromejia.marvelapp.domain.models.search.DRecentSearch
import com.alejandromejia.marvelapp.ui.components.models.ComponentsView
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.utils.FontFamilyProvider
import com.alejandromejia.marvelapp.utils.RECENT_SEARCH

@Composable
fun RecentSearchScreenView(
    componentView: ComponentsView,
    viewModel: ViewModels,
    recentSearch: List<DRecentSearch>?
) {
    Box(
        modifier = Modifier
            .background(componentView.background)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = RECENT_SEARCH,
                color = Color.White,
                letterSpacing = 0.5.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                fontFamily = FontFamilyProvider.openSans,
                fontWeight = FontWeight.ExtraBold
            )
            if (recentSearch?.isNotEmpty() == true) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                ) {
                    items(recentSearch) { item ->
                        ItemsRecentSearch(
                            item = item,
                            viewModel = viewModel
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    }
}
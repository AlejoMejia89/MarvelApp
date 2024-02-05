package com.alejandromejia.marvelapp.domain.navigation

import com.alejandromejia.marvelapp.utils.SCAFFOLD_DETAIL_VIEW
import com.alejandromejia.marvelapp.utils.SCAFFOLD_MAIN_VIEW

sealed class Routes(val route: String) {
    object ScaffoldMainView : Routes(SCAFFOLD_MAIN_VIEW)
    object ScaffoldDetailView : Routes(SCAFFOLD_DETAIL_VIEW)
}

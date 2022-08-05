package com.blez.bleachfandom.presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.blez.bleachfandom.Navigation.Screen
import com.blez.bleachfandom.presentation.common.ListContent

@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searhQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            SearchTopBar(text = searhQuery,
                onTextChanged = { searchViewModel.updatesearchQuery(query = it) },
                onSearchClicked = {
                    searchViewModel.searchHeroes(it)
                },
                onCloseClicked = { navHostController.popBackStack() })
        }, content ={paddingValues -> Column(
            Modifier.fillMaxSize()
            .padding(paddingValues)) {
            ListContent(heroes = heroes, navHostController =navHostController ) }  }
    )
}

package com.blez.bleachfandom.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.blez.bleachfandom.Navigation.Screen
import com.blez.bleachfandom.presentation.common.ListContent


@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController
){
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {
                navHostController.navigate(Screen.Search.route)
            })
        }, content = {paddingValues -> Column(Modifier.fillMaxSize()
            .padding(paddingValues)) {
            ListContent(heroes = allHeroes, navHostController =navHostController ) }  }


    )
}

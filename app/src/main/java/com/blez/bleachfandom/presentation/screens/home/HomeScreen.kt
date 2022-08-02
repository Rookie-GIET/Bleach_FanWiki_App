package com.blez.bleachfandom.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.blez.bleachfandom.presentation.common.ListContent


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController
){
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
 /*Scaffold(
     topBar = {
         HomeTopBar(onSearchClicked = {})
     },
     content = {
         ListContent(
             heroes = allHeroes ,
             navHostController = navHostController
         ) }
 )*/
}


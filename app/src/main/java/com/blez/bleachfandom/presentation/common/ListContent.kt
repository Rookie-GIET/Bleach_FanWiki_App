package com.blez.bleachfandom.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberImagePainter
import com.blez.bleachfandom.Navigation.Screen
import com.blez.bleachfandom.domain.model.Hero
import com.blez.bleachfandom.ui.theme.HERO_ITEM_HEIGHT
import com.blez.bleachfandom.ui.theme.Shapes

@Composable
fun ListContent(
    heroes : LazyPagingItems<Hero>,
    navHostController: NavHostController
)
{

}

@Composable
fun HeroItem(
    hero : Hero,
    navHostController: NavHostController
)
{


    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable { navHostController.navigate(Screen.Details.passHeroId(hero.id)) }){
         Surface(shape = Shapes.large) {
           /*  Image(painter = , contentDescription = )*/
             
         }
    }
}
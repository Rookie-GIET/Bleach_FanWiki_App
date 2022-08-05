package com.blez.bleachfandom.presentation.common


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.blez.bleachfandom.Navigation.Screen
import com.blez.bleachfandom.R
import com.blez.bleachfandom.domain.model.Hero
import com.blez.bleachfandom.presentation.components.RatingWidget
import com.blez.bleachfandom.presentation.components.ShimmerEffect
import com.blez.bleachfandom.ui.theme.*
import com.blez.bleachfandom.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
    heroes : LazyPagingItems<Hero>,
    navHostController: NavHostController
)
{



    Log.d("LIST_CONTENT", heroes.loadState.toString())
    val result = handlePagingResult(heroes = heroes)
    if (result){
        LazyColumn(contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)){
            items(
                items = heroes,
                key = { hero ->
                    hero.id
                }
            ){
                    hero ->
                hero?.let {
                    HeroItem(hero = it, navHostController = navHostController)
                }
            }
        }
    }



}
@Composable
fun handlePagingResult(
    heroes:LazyPagingItems<Hero>
): Boolean{
    heroes.apply {
        val error = when{
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        return when{
            loadState.refresh is LoadState.Loading ->{
                ShimmerEffect()
                false
            }
            error != null ->{
                EmptyScreen(error = error)
                false
            }
            heroes.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun HeroItem(
    hero : Hero,
    navHostController: NavHostController
)
{

    val painter = rememberAsyncImagePainter(model = "$BASE_URL${hero.image}",
        placeholder = painterResource(R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder))
    painter







    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable { navHostController.navigate(Screen.Details.passHeroId(hero.id)) },
        contentAlignment = Alignment.BottomStart)
    {
         Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(modifier = Modifier.fillMaxSize(),painter = painter ,
                contentDescription = stringResource(id = R.string.hero_image),
                contentScale = ContentScale.Crop )
             
         }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(bottomEnd = LARGE_PADDING, bottomStart = LARGE_PADDING)
        ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)) {
                    Text(text = hero.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = hero.about,
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(Modifier.padding(top = SMALL_PADDING),
                        verticalAlignment = Alignment.CenterVertically ) {
                        RatingWidget(modifier = Modifier.padding(end = SMALL_PADDING), 
                            rating = hero.rating)
                        Text(text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium))

                    }


                }
        }
    }
}
@ExperimentalCoilApi
@Preview
@Composable
fun HeroItemPreview() {
    HeroItem(hero = Hero(id = 1, name = "Ichigo",
                image = "",about ="Goku is number 1", rating = 4.0, power = 120, month = "Augest",
        day = "12th", listOf(""), listOf(""),"Getsuka tensho"
                ), navHostController = rememberNavController() )
    
}
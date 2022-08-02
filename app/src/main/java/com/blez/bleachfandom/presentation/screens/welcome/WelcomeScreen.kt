package com.blez.bleachfandom.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.blez.bleachfandom.Navigation.Screen
import com.blez.bleachfandom.R
import com.blez.bleachfandom.domain.model.OnBoardiingPage
import com.blez.bleachfandom.ui.theme.*
import com.blez.bleachfandom.util.Constants.LAST_ON_BOARDING_PAGE
import com.blez.bleachfandom.util.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(navHostController: NavHostController,
                  welcomeViewModel: WelcomeViewModel = hiltViewModel())
{
    val pages = listOf(
        OnBoardiingPage.First,
        OnBoardiingPage.Second,
        OnBoardiingPage.Third
    )
    val pageState = rememberPagerState()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)) { 
        HorizontalPager(modifier = Modifier.weight(10f),
            count =ON_BOARDING_PAGE_COUNT, state = pageState,
            verticalAlignment = Alignment.Top ) {position ->
            PagerScreen(onBoardiingPage = pages[position] )
        }
            HorizontalPagerIndicator(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                pagerState = pageState,
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH ,
            indicatorHeight = PAGING_INDICATOR_SPACING)
        FinishButton(
            modifier = Modifier.weight(1f),
            pageState = pageState) {
            navHostController.popBackStack()
            navHostController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
        }
        }
    }

@Composable
fun PagerScreen(onBoardiingPage: OnBoardiingPage){
    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top) {
      Image( modifier = Modifier
          .fillMaxWidth(0.5f)
          .fillMaxHeight(0.7f),painter = painterResource(id = onBoardiingPage.image ) , contentDescription = R.string.onBoardingImage.toString())
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = EXTRA_LARGE_PADDING)
                            .padding(top = SMALL_PADDING),
                        text = onBoardiingPage.title,
                        color = MaterialTheme.colors.titleColor,
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardiingPage.description,
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

            
        }
    }

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(modifier: Modifier,
   pageState : PagerState,
   onClick:() -> Unit,
){
    Row(modifier = Modifier.padding(horizontal = EXTRA_LARGE_PADDING),
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.Center) {
        AnimatedVisibility(modifier = Modifier.fillMaxWidth(),
            visible = pageState.currentPage == LAST_ON_BOARDING_PAGE) {
            Button(onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor
            )) {
                Text(text = "Finish")

            }
            
        }
    }
}




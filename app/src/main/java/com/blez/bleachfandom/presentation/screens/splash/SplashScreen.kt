package com.blez.bleachfandom.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.media.MediaPlayer
import android.view.animation.OvershootInterpolator

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.blez.bleachfandom.Navigation.Screen
import com.blez.bleachfandom.R

@Composable
fun SplashScreen(navHostController: NavHostController,
                         splashViewModel: SplashViewModel = hiltViewModel()){
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    val rotate = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true)
    {
        rotate.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200, easing = {
                    OvershootInterpolator(4f).getInterpolation(it)

                }
            )
        )
        navHostController.popBackStack()
        if (onBoardingCompleted){

            navHostController.navigate(Screen.Home.route)
        }else{
            navHostController.navigate(Screen.Welcome.route)
        }
    }

    Splash(degree = rotate.value)


}


@Composable
 fun Splash(degree : Float,)
{
    if(!isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(Color.White)
                //.background(Brush.verticalGradient(listOf(Purple500, Purple700)))
                .fillMaxSize(),
            Alignment.Center


        ) {
            Image(modifier = Modifier.rotate(degrees = degree).fillMaxSize(),
                painter = painterResource(id = R.drawable.hollow_mask),
                contentDescription = stringResource(R.string.app_logo),contentScale = ContentScale.Crop,
            )
        }
    }
    else
    {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            Alignment.Center


        ) {
            Image(modifier = Modifier.rotate(degrees = degree).fillMaxSize(),
                painter = painterResource(id = R.drawable.hollow_mask),
                contentDescription = stringResource(R.string.app_logo),contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(degree = 20f)
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash(degree = 20f)
    
}

package com.blez.bleachfandom.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blez.bleachfandom.R
import com.blez.bleachfandom.ui.theme.Purple500
import com.blez.bleachfandom.ui.theme.Purple700

@Composable
fun SplashScreen(navHostController: NavHostController){

    Splash()
}


@Composable
fun Splash()
{
    if(!isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(Color.White)
                //.background(Brush.verticalGradient(listOf(Purple500, Purple700)))
                .fillMaxSize(),
            Alignment.Center


        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_hollow_mask_logo),
                contentDescription = stringResource(R.string.app_logo),contentScale = ContentScale.Crop,modifier = Modifier.fillMaxSize()
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
            Image(
                painter = painterResource(id = R.drawable.ic_hollow_mask_logo),
                contentDescription = stringResource(R.string.app_logo),contentScale = ContentScale.Crop,modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash()
    
}

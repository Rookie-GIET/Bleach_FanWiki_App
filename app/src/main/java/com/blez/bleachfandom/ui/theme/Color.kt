package com.blez.bleachfandom.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val White = Color(0xFFFFFFFF)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8D)
val DarkGray = Color(0xFF2A2A2A)
val StarColor = Color(0xFFFFC94D)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)



val Colors.welcomeScreenBackgroundColor
    @Composable
get()  = if (isLight) Color.White else Color.Black

val Colors.titleColor
    @Composable
    get() = if (isLight) DarkGray else LightGray
val Colors.descriptionColor
    @Composable
    get() = if (isLight) DarkGray else LightGray
val Colors.activeIndicatorColor
    @Composable
    get() = if (isLight) Purple500 else Purple700
val Colors.inactiveIndicatorColor
@Composable
get() = if (isLight) DarkGray else LightGray
val Colors.buttonBackgroundColor
    @Composable
    get() = if (isLight) Purple500 else Purple700
val Colors.topAppBarContentColor
    @Composable
    get() = if (isLight) White else LightGray
val Colors.topAppBarBackgroundColor
    @Composable
    get() = if (isLight) Purple500 else Color.Black
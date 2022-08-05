package com.blez.bleachfandom.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import com.blez.bleachfandom.R
import com.blez.bleachfandom.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.blez.bleachfandom.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error : LoadState.Error? = null){
    var message by remember {
        mutableStateOf("Find Your Favourite Hero!")
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_search_document)

    }
    if (error!= null){
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_network_error
    }

    var startAnim by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(targetValue =
    if (startAnim) ContentAlpha.disabled else 0f,
    animationSpec = tween(
        durationMillis = 1000))
    LaunchedEffect(key1 = true)
    {
        startAnim = true
    }


    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(modifier = Modifier.size(NETWORK_ERROR_ICON_HEIGHT)
            .alpha(alpha = alphaAnim),painter = painterResource(id = icon ) , contentDescription = stringResource(id = R.string.network_error_icon),
        tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray)
        Text(modifier = Modifier.padding(all = SMALL_PADDING)
            .alpha(alpha = alphaAnim),
            text = message,
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray )


    } 
}

fun parseErrorMessage(error: LoadState.Error): String{
    return when{
        error.error is SocketTimeoutException ->{
            "Sever Unavailable"
        }
        error.error is ConnectException ->{
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error"
        }
    }
}



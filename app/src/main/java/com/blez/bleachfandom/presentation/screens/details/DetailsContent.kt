package com.blez.bleachfandom.presentation.screens.details

import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.blez.bleachfandom.R
import com.blez.bleachfandom.R.string.close_icon
import com.blez.bleachfandom.domain.model.Hero
import com.blez.bleachfandom.presentation.components.InfoBox
import com.blez.bleachfandom.presentation.components.OrderedList
import com.blez.bleachfandom.ui.theme.*
import com.blez.bleachfandom.util.Constants.BASE_URL
import com.blez.bleachfandom.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.blez.bleachfandom.util.Constants.MIN_SHEET_SIZE
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalCoilApi::class)
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navHostController: NavHostController,
   selectedHero : Hero?,
    colors : Map<String,String>
){
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedHero) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }
    val systemUIcontroller = rememberSystemUiController()
    systemUIcontroller.setStatusBarColor(
       color = Color(parseColor(darkVibrant))
    )


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Expanded)
    )
    val currentSheetFraction = scaffoldState.currentSheetFraction
    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(sheetShape = RoundedCornerShape(
        topStart = radiusAnim,
        topEnd = radiusAnim
        ),scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_SIZE,
            sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it,
                infoBoxIconColor = Color(parseColor(vibrant)),
                sheetBackgroundColor = Color(parseColor(darkVibrant)),
                contentColor = Color(parseColor(onDarkVibrant))

            ) }
        },
    content = {
        selectedHero?.let { hero ->
            BackgroundContent(
                heroImage = hero.image,
                backgroundColor = Color(parseColor(darkVibrant)),
                imageFraction = currentSheetFraction,
                onCloseClicked = {
                    navHostController.popBackStack()
                })
        }
    })
}







@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor : Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
){
    Column(modifier = Modifier
        .background(sheetBackgroundColor)
        .padding(all = LARGE_PADDING),) {


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = LARGE_PADDING),
        verticalAlignment = Alignment.CenterVertically) {
            Icon(modifier = Modifier
                .size(INFO_ICON_SIZE)
                .weight(2f), tint = contentColor
                ,painter = painterResource(id = R.drawable.power),
                contentDescription = stringResource(id = R.string.app_logo)
            )
            Text(modifier = Modifier
                .weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold

            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween) {
            InfoBox(icon = painterResource(id = R.drawable.power) ,
                iconColor = infoBoxIconColor ,
                bigText = "${selectedHero.power}" ,
                smallText = stringResource(R.string.power) ,
                textColor = contentColor)
            InfoBox(icon = painterResource(id = R.drawable.ic_calendar) ,
                iconColor = infoBoxIconColor ,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.month) ,
                textColor = contentColor)
            InfoBox(icon = painterResource(id = R.drawable.ic_cake) ,
                iconColor = infoBoxIconColor ,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday) ,
                textColor = contentColor)


        }
        Text(modifier = Modifier.fillMaxWidth(),text = stringResource(R.string.about),
        color = contentColor,
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        fontWeight = FontWeight.Bold)
        Text(modifier = Modifier
            .alpha(ContentAlpha.medium)
            .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize
        )
        val scrollState = rememberScrollState()

        Row(modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(end = SMALL_PADDING)
        , horizontalArrangement = Arrangement.SpaceBetween) {


                OrderedList(title = stringResource(R.string.family),
                    items = selectedHero.family,
                   textColor = contentColor)


            OrderedList(title = stringResource(R.string.abilities),
                items = selectedHero.ablitites,
                textColor = contentColor)
            OrderedList(title = stringResource(R.string.bankai),
                items = listOf(selectedHero.bankai),
                textColor = contentColor)

        }




    }
}
@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberAsyncImagePainter(model = imageUrl, error = painterResource(id = R.drawable.ic_placeholder))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, content = {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(close_icon),
                    tint = Color.White
                )
            }
        })
    }
}




@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 1f
            currentValue == Expanded && targetValue == Expanded -> 0f
            currentValue == Collapsed && targetValue == Expanded -> 1f - fraction
            currentValue == Expanded && targetValue == Collapsed -> 0f + fraction
            else -> fraction
        }
    }




@Preview
@Composable
fun PreviewInfoBox() {
    BottomSheetContent(
     Hero(
         id = 6,
         name = "Orihime Inoue",
         image = "/heroes/Orihime_Inoue.jpg",
         about = "Orihime Inoue (井上 織姫, Inoue Orihime) is a Human living in Karakura Town. She is a former student of Karakura High School. She is married to Ichigo Kurosaki and has a son named Kazui Kurosaki.",
         rating = 4.8,
         power = 70,
         month = "September",
         day = "3th",
         family = listOf(
             "Ichigo Kurosaki (Husband)",
             "Kazui Kurosaki (Son)",
             "Isshin Kurosaki (Father-in-law)",
             "Masaki Kurosaki (Mother-in-law, deceased)",
             "Karin Kurosaki (Sister-in-law)",
             "Yuzu Kurosaki (Sister-in-law)",
             "Sora Inoue (Older Brother, deceased)"
         ),
         ablitites = listOf(
             "Spiritual Awareness",
             "Healer",
             "Empathy"
         ),
         bankai = "Shun Shun Rikka"
     )
    )

}

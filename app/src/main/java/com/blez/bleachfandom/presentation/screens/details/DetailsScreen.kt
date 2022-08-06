package com.blez.bleachfandom.presentation.screens.details

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.blez.bleachfandom.domain.model.Hero
import com.blez.bleachfandom.util.Constants.BASE_URL
import com.blez.bleachfandom.util.PaletteGenerator.convertImageUrlToBitmap
import com.blez.bleachfandom.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest


@ExperimentalMaterialApi
@Composable
fun DetailsScreen(navHostController: NavHostController,
                  detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel(),
){
    val sample =  Hero(
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
    val selectedHero by detailsScreenViewModel.selectedHero.collectAsState(initial = sample)
    val colorPalette by detailsScreenViewModel.colorPalette

    if (colorPalette.isNotEmpty()){
        DetailsContent(navHostController = navHostController, selectedHero = selectedHero, colors = colorPalette )
    }else{
        detailsScreenViewModel.generatedColorPalette()
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = true){
        detailsScreenViewModel.uiEvent.collectLatest {event ->
           when(event){
               is UiEvent.GenerateColorPalette -> {
                   val bitmap = convertImageUrlToBitmap(
                        imageURL = "$BASE_URL${selectedHero?.image}",
                       context
                   )
                   if(bitmap !=null){
                       detailsScreenViewModel.setColorPalette(
                           color = extractColorsFromBitmap(bitmap)
                       )
                   }
               }
           }
        }
    }

    Log.d("key","{${selectedHero}}")



}
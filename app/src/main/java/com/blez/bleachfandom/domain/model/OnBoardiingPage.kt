package com.blez.bleachfandom.domain.model

import android.media.Image
import androidx.annotation.DrawableRes
import com.blez.bleachfandom.R


sealed class OnBoardiingPage(
    @DrawableRes
    val image:Int,
    val title:String,
    val description : String
){
    object First : OnBoardiingPage(
        image = R.drawable.ic_greetings_background,
        title = "Greetings",
        description = "Are you a Bleach fan? Because if you are then we have a great new for you!"
    )
    object Second : OnBoardiingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find your favourite heroes and learn some of the things that you don't know about."
    )
    object Third : OnBoardiingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Check out their power and see how much are they strong comparing to others."
    )
}

package com.blez.bleachfandom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blez.bleachfandom.Navigation.SetupNavGraph
import com.blez.bleachfandom.ui.theme.BleachFandomTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPagerApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navControlller : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BleachFandomTheme {
                navControlller= rememberNavController()
                SetupNavGraph(navController = navControlller)

            }
        }
    }
}


package com.aimicor.berealtechtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.aimicor.berealtechtest.imagefolder.presentation.IMAGE_FOLDER_FEATURE
import com.aimicor.berealtechtest.imagefolder.presentation.imageFolderGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            AnimatedNavHost(
                navController = navController,
                startDestination = IMAGE_FOLDER_FEATURE
            ) {
                imageFolderGraph(navController)
                // ... more feature graphs
            }
        }
    }
}

package com.example.kinetic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.kinetic.presentation.main.MainScreen
import com.example.kinetic.presentation.navigation.MainNavGraph
import com.example.kinetic.presentation.ui.theme.KineticTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var darkTheme by mutableStateOf(false)
    private var dynamicColor by mutableStateOf(false)
    private var followSystem by mutableStateOf(true)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            KineticTheme(
                darkTheme = if(followSystem) isSystemInDarkTheme() else darkTheme,
                dynamicColor = dynamicColor
            ) {
                MainScreen(
                    navController = rememberAnimatedNavController(),
                    onLightOn = {
                        followSystem = false
                        darkTheme = false
                        dynamicColor = false },
                    onDarkOn = {
                        followSystem = false
                        darkTheme = true
                        dynamicColor = false },
                    onDynamicColorOn = {
                        followSystem = false
                        dynamicColor = true
                    },
                    onFollowSystem = {
                        followSystem = true
                    }
                )
            }
        }
    }
}
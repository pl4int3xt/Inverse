package com.example.kinetic

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.kinetic.presentation.navigation.MainNavGraph
import com.example.kinetic.presentation.ui.theme.KineticTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var darkTheme by mutableStateOf(false)
    var dynamicColor by mutableStateOf(false)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            KineticTheme(
                darkTheme = darkTheme,
                dynamicColor = dynamicColor
            ) {
                MainNavGraph(
                    navHostController = rememberAnimatedNavController(),
                    onLightOn = {
                        darkTheme = false
                        dynamicColor = false },
                    onDarkOn = {
                        darkTheme = true
                        dynamicColor = false },
                    onDynamicColorOn = {
                        dynamicColor = true
                    }
                )
            }
        }
    }
}
package com.example.kinetic.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.kinetic.presentation.main.BottomNavItem
import com.example.kinetic.presentation.main.BottomNavigationBar
import com.example.kinetic.presentation.main.MainScreen
import com.example.kinetic.presentation.screen.Screens
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    onDarkOn: () -> Unit,
    onLightOn: () -> Unit,
    onFollowSystem: () -> Unit,
    onDynamicColorOn: () -> Unit
) {
    AnimatedNavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ){
        genresNavGraph(navHostController)
        detailsNavGraph(navHostController)
        homeNavGraph(
            onDarkOn, onLightOn, onFollowSystem, onDynamicColorOn, navHostController
        )
    }
}
package com.example.kinetic.presentation.settings.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kinetic.presentation.game_details.GamesDetailsScreenEvents
import com.example.kinetic.presentation.settings.SettingsScreenEvents
import com.example.kinetic.presentation.settings.SettingsScreenViewModel
import com.example.kinetic.presentation.shared.MainTopAppBar
import com.example.kinetic.presentation.uievent.UiEvent
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onLightOn: () -> Unit,
    onDarkOn: () -> Unit,
    onDynamicColorOn: () -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: SettingsScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.PopBackStack -> { onPopBackStack() }
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "",
                navigationIcon = Icons.Default.ArrowBack,
                onClickNavigation = { viewModel.onEvent(SettingsScreenEvents.OnPopBackStack) },
                onClickAction = { },
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(top = it.calculateTopPadding())
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Customization",
                    fontSize = 30.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
                ElevatedButton(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onEvent(SettingsScreenEvents.OnDialogStateChanged(true)) }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(imageVector = Icons.Filled.Brush, contentDescription = "customize")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Themes",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                ElevatedButton(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ }) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Language",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "(System Default)")
                    }
                }
            }
            if (viewModel.dialogState){
                Dialog(
                    onDismissRequest = {
                        viewModel.onEvent(SettingsScreenEvents.OnDialogStateChanged(false))
                    },
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                ) {
                    ElevatedCard {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            SingleMode(selected = viewModel.darkMode,
                                onClick = {
                                    viewModel.onEvent(SettingsScreenEvents.OnDarkModeSelected(!viewModel.darkMode))
                                },
                                icon = Icons.Filled.DarkMode,
                                title = "Dark Mode"
                            )
                            SingleMode(selected = viewModel.lightMode,
                                onClick = {
                                    viewModel.onEvent(SettingsScreenEvents.OnLightModeYouSelected(!viewModel.lightMode))
                                },
                                icon = Icons.Filled.LightMode,
                                title = "Light Mode"
                            )
                            SingleMode(selected = viewModel.systemSettings,
                                onClick = {
                                    viewModel.onEvent(SettingsScreenEvents.OnUseSystemSettingsSelected(!viewModel.systemSettings))
                                },
                                icon = Icons.Filled.Settings,
                                title = "Follow system"
                            )
                            SingleMode(selected = viewModel.materialYou,
                                onClick = {
                                    viewModel.onEvent(SettingsScreenEvents.OnMaterialYouSelected(!viewModel.materialYou))
                                },
                                icon = Icons.Filled.Brush,
                                title = "Material You"
                            )
                        }
                    }
                }
            }
        }
    }
}
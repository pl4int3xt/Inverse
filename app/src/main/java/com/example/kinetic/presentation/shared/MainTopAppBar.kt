package com.example.kinetic.presentation.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    title: String? = "",
    navigationIcon: ImageVector? = null,
    actions: ImageVector? = null,
    onClickNavigation: () -> Unit,
    onClickAction: () -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = Modifier.padding(5.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent),
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = { onClickNavigation() }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.tertiary,
                        imageVector = navigationIcon, contentDescription = "")
                }
            }
        },
        actions = {
            if (actions != null) {
                IconButton(onClick = { onClickAction() }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.tertiary,
                        imageVector = actions, contentDescription = "")
                }
            }
        },
        title = {
            if (title != null) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    )
}
package com.example.kinetic.presentation.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    text: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = Modifier.padding(5.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.White
        ),
        actions = {
            Surface (
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                ,
                color = MaterialTheme.colorScheme.secondary
            ){
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colorScheme.tertiary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    value = text, onValueChange = {
                        onTextChange(it)
                    },
                    placeholder = {
                        Text(
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.alpha(ContentAlpha.medium),
                            text = "search game here ....")},
                    singleLine = true,
                    leadingIcon = {
                        IconButton(
                            modifier = Modifier.alpha(ContentAlpha.medium),
                            onClick = { /*TODO*/ }) {
                            Icon(
                                tint = MaterialTheme.colorScheme.tertiary,
                                imageVector = Icons.Default.Search,
                                contentDescription = "search icon"
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (text.isNotEmpty()){
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                }
                            }) {
                            Icon(
                                tint = MaterialTheme.colorScheme.tertiary,
                                imageVector = Icons.Default.Close,
                                contentDescription = "close icon"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchClicked(text)
                            keyboardController?.hide()
                        }
                    )
                )
            }
        },
        title = {

        }
    )
}
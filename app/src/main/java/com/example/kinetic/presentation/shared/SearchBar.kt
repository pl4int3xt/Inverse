package com.example.kinetic.presentation.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    onSearchClicked: (String) -> Unit,
    onResetSearchState: () -> Unit
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
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    value = text, onValueChange = {
                        onTextChange(it)
                    },
                    placeholder = {
                        Text(
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.alpha(0.5f),
                            text = "search game here ....")},
                    singleLine = true,
                    leadingIcon = {
                        IconButton(
                            modifier = Modifier.alpha(0.5f),
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
                                    onResetSearchState()
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
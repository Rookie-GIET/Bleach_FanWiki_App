package com.blez.bleachfandom.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.blez.bleachfandom.R
import com.blez.bleachfandom.ui.theme.topAppBarBackgroundColor
import com.blez.bleachfandom.ui.theme.topAppBarContentColor
import com.blez.bleachfandom.util.Constants.TOP_APP_BAR_HEIGHT


@Composable
fun SearchTopBar(
    text : String,
    onTextChanged : (String) -> Unit,
    onSearchClicked : (String) -> Unit,
    onCloseClicked : () -> Unit,
    ){
        SearchWidget(text = text, onTextChanged = onTextChanged , onSearchClicked =onSearchClicked,onCloseClicked)
}
@Composable
fun SearchWidget(
    text : String,
    onTextChanged : (String) -> Unit,
    onSearchClicked : (String) -> Unit,
    onCloseClicked : () -> Unit,
){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(TOP_APP_BAR_HEIGHT),
    elevation = AppBarDefaults.TopAppBarElevation,
    color = MaterialTheme.colors.topAppBarBackgroundColor) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {onTextChanged(it)},
        placeholder = {
            Text(modifier = Modifier.alpha(ContentAlpha.medium),
                text = stringResource(R.string.search_here),
            color = Color.White)
        }, textStyle = androidx.compose.ui.text.TextStyle(color = MaterialTheme.colors.topAppBarContentColor),
            singleLine = true, leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium)
                    ,onClick = {}) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon),
                    tint = MaterialTheme.colors.topAppBarContentColor)
                }
            }, trailingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium)
                    ,onClick = {
                        if(text.isNotEmpty())
                            onTextChanged("")
                        else
                            onCloseClicked()
                    }) {
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.search_close),
                        tint = MaterialTheme.colors.topAppBarContentColor)
                }
            }, keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppBarContentColor
            )
        )
    }
}

@Preview
@Composable
fun SearchFunctionPreview() {
    SearchWidget(text = "", onTextChanged = {}, onSearchClicked = {}) {}
    
}
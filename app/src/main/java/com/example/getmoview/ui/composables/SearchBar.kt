package com.example.getmoview.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes

@Composable
fun SearchBar(
    searchTerm: MutableState<String>,
    modifier: Modifier = Modifier
) {
    val searchDefaultLabel = "Search"
    val label = remember { mutableStateOf(searchDefaultLabel) }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier.background(
            Color.LightGray,
            RoundedCornerShape(15.dp)
        )
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp)
            )

            Box(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
                Text(
                    text = label.value,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = 16.sp
                )

                BasicTextField(
                    value = searchTerm.value,
                    onValueChange = {
                        searchTerm.value = it
                        if (searchTerm.value !== "") label.value = "" else label.value =
                            searchDefaultLabel
                    },
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                    singleLine = true,
                    enabled = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

            }
        }
    }
}

@Composable
fun StaticSearchBar(
    navController: NavController
) {

//    val searchedMovies = searchViewModel.searchState.value
//    val searchTerm = remember {
//        mutableStateOf(TextFieldValue(""))
//    }
    Box(
        modifier = Modifier
            .background(
                Color.LightGray,
                RoundedCornerShape(15.dp)
            )
            .fillMaxWidth()
            .clickable {
                navController.navigate(BottomNavigationRoutes.SearchScreen.routes)
            }
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.Black)
            Text(
                text = "Search Movie",
                modifier = Modifier.padding(start = 10.dp),
                color = Color.Black
            )
        }
    }
}
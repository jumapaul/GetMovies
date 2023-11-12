package com.example.getmoview.ui.screens.routes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigator(navController: NavController, bottomBarState: MutableState<Boolean>) {
    val items = listOf(
        BottomNavigationRoutes.MovieScreen,
        BottomNavigationRoutes.FavoriteScreen,
        BottomNavigationRoutes.SettingScreen,
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(backgroundColor = Color.White) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { items ->
                    BottomNavigationItem(
                        selected = currentRoute == items.routes, onClick = {
                            navController.navigate(items.routes) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            items.icon?.let { imageVector: ImageVector ->  (imageVector) }?.let {
                                Icon(imageVector = it, contentDescription = null)
                            }
                        },
                        label = { items.title?.let { Text(text = it) } },
                        alwaysShowLabel = true
                    )
                }
            }
        })
}

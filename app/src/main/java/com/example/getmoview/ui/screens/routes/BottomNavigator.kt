package com.example.getmoview.ui.screens.routes

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigator(navController: NavController) {
    val items = listOf(
        BottomNavigationRoutes.MovieScreen,
        BottomNavigationRoutes.FavoriteScreen,
        BottomNavigationRoutes.AccountScreen
    )

    BottomNavigation(backgroundColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { items ->
            BottomNavigationItem(selected = currentRoute == items.routes, onClick = {
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
                    Icon(
                        painter = painterResource(id = items.icon),
                        contentDescription = null
                    )
                },
                label = {Text(text = items.title)},
                alwaysShowLabel = true
            )
        }
    }
}

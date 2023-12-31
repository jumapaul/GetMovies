package com.example.getmoview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.getmoview.ui.screens.routes.BottomNavigationGraph
import com.example.getmoview.ui.screens.routes.BottomNavigationRoutes
import com.example.getmoview.ui.screens.routes.BottomNavigator
import com.example.getmoview.ui.theme.GetMoviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetMoviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainView() {

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.isAppearanceLightNavigationBars = true

        val navController = rememberNavController()
        val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            BottomNavigationRoutes.MovieDetails.routes + "/{movies}" -> {
                bottomBarState.value = false
            }

            BottomNavigationRoutes.ShowsDetail.routes + "/{shows}" ->{
                bottomBarState.value = false
            }

            BottomNavigationRoutes.UpcomingMovies.routes->{
                bottomBarState.value = false
            }

            BottomNavigationRoutes.TopRatedMoviesAndTvShows.routes->{
                bottomBarState.value = false
            }

            BottomNavigationRoutes.PopularMoviesAndTvShows.routes->{
                bottomBarState.value = false
            }


            else -> {
                bottomBarState.value = true
            }
        }

        Scaffold(
            bottomBar = {
                if (bottomBarState.value) {
                    BottomNavigator(
                        navController = navController, bottomBarState
                    )
                }
            }, modifier = Modifier.padding(top = 20.dp, bottom = 5.dp)
        ) { padding ->
            BottomNavigationGraph(
                navHostController = navController,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth()
            )
        }

    }
}

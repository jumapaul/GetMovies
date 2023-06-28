package com.example.getmoview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.getmoview.ui.screens.movie.MovieScreen
import com.example.getmoview.ui.screens.movie.MovieViewModel
import com.example.getmoview.ui.screens.routes.BottomNavigationGraph
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
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainView() {
      val navController = rememberNavController()

        Scaffold(bottomBar = { BottomNavigator(navController = navController)}) {padding ->
            BottomNavigationGraph(navHostController = navController, modifier = Modifier.padding(padding))

        }

    }
}

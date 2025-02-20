package com.example.movieapp.navagation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.model.Movie

import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.details.DetailsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(
        title = { Text("MovieApp") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    val canNavigateBack = navController.currentBackStackEntry != null
    Log.d("canNavigateBack",canNavigateBack.toString());
    Scaffold(
        topBar = {
            MovieAppBar(
                currentScreen = "Movie App",
                canNavigateBack = true, //navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
            )
        }
    ){  innerPadding ->
        NavHost(navController = navController,
            startDestination = MovieScreens.HomeScreen.name ,  modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {

            composable(MovieScreens.HomeScreen.name){
                //here we pass where this should lead us to
                HomeScreen(navController = navController)
            }

            //www.google.com/detail-screen/id=34
            //argument on route with name and type in list of argument
            composable(MovieScreens.DetailScreen.name+"/{movie}",
                arguments = listOf(navArgument(name = "movie") {type = NavType.StringType})) {
                    backStackEntry ->

                DetailsScreen(navController = navController,
                    backStackEntry.arguments?.getString("movie"))
            }


        }

    }


}
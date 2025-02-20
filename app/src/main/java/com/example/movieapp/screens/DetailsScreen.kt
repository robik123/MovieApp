package com.example.movieapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun DetailsScreen(navController: NavController,
                  movieId: String?) {
    val movieList = getMovies().filter { movie ->
          movie.id == movieId
    }

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text( buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Blue,
                            fontSize = 24.sp)) {
                            append("Plot: ")
                        }
                        withStyle(style = SpanStyle(color = Color.Blue,
                            fontSize = 20.sp)
                        ) {
                            append(movieList[0].plot)
                        }

                    }, modifier = Modifier.padding(6.dp))

                    HorizontalDivider(modifier = Modifier.padding(3.dp))
                    Text(text = "Director: ${movieList[0].director}", style = MaterialTheme.typography.titleLarge)
                    Text(text = "Actors: ${movieList[0].actors}", style = MaterialTheme.typography.titleLarge)
                    Text(text = "Rating: ${movieList[0].rating}", style = MaterialTheme.typography.titleLarge)


                }




}

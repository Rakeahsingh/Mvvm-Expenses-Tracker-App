package com.example.mvvmexpensetrackerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmexpensetrackerapp.core.navigation.Route
import com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.home_screen.HomeScreen
import com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.UserInterface.SinInScreen
import com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.UserInterface.SinUpScreen
import com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.splash_screen.SplashScreen
import com.example.mvvmexpensetrackerapp.ui.theme.MvvmExpenseTrackerAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmExpenseTrackerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState
                    ) {

                        NavHost(navController = navController, startDestination = Route.SplashScreen){
                            composable(Route.SplashScreen){
                                SplashScreen(onNavigate = {
                                    navController.navigate(it.route)
                                })
                            }

                            composable(Route.SinUpScreen){
                                SinUpScreen(onNavigate = {
                                    navController.navigate(it.route)
                                })
                            }

                            composable(Route.SinInScreen){
                                SinInScreen(onNavigate = {
                                    navController.navigate(it.route)
                                })
                            }

                            composable(Route.HomeScreen){
                                HomeScreen(navController = navController)
                            }

                        }
                    }

                }
            }
        }
    }
}


package com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmexpensetrackerapp.core.navigation.Route
import com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.UserInterface.UserViewModel

@Composable
fun HomeScreen(
    viewModel: UserViewModel = hiltViewModel(),
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = viewModel.currentUser?.displayName ?: "")
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = viewModel.currentUser?.email ?: "" )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                viewModel.sinOut()
                navController.navigate(Route.SinInScreen)
            }) {
                Text(text = "Logout")
            }

        }

    }
}
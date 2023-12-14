package com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.UserInterface

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmexpensetrackerapp.core.navigation.Route
import com.example.mvvmexpensetrackerapp.core.utils.Resources
import com.example.mvvmexpensetrackerapp.core.utils.UiEvent

@Composable
fun SinInScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val loginFlow = viewModel.loginFlow.collectAsState()

    var email by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "SinIn Here",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "SinIn to Our Expense Tracker App",
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Enter Email")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Person_icon")
            }, modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(8.dp)
                ))
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = password,
            onValueChange ={password = it},
            label = {
                androidx.compose.material.Text(text = "Enter Password")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Key, contentDescription = "Person_icon")
            }, modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.sinInUser(email, password)
        }
        ) {
            Text(text = "SinIn")
        }

        Row {
            Text(text = "New User?..")
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "SinUp Here",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    onNavigate(UiEvent.Navigate(Route.SinUpScreen))
                })
        }


        loginFlow.value?.let {
            when(it){
                is Resources.Success -> {

                    Toast.makeText(context, "SinIn Successfully", Toast.LENGTH_SHORT).show()
                        onNavigate(UiEvent.Navigate(Route.HomeScreen))

                }
                is Resources.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resources.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }

    }

}
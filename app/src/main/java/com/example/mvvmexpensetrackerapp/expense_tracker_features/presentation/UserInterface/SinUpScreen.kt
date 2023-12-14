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
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
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
import kotlinx.coroutines.launch

@Composable
fun SinUpScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: UserViewModel = hiltViewModel()
) {

    var name by remember{
        mutableStateOf("")
    }
    var email by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }

    val context = LocalContext.current

    val sinUpFlow = viewModel.sinUpFlow.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "SinUp Here",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Welcome to Expense Tracker SinUp Page",
            fontSize = 18.sp,
            )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(value = name,
            onValueChange = { name = it },
            label = {
                Text(text = "Enter Name")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person_icon")
            }, modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
            )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = {
                Text(text = "Enter Email")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Person_icon")
            }, modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = password,
            onValueChange ={password = it},
            label = {
                Text(text = "Enter Password")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Key, contentDescription = "Person_icon")
            }, modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.sinUpUser(name, email, password)
            }
        ) {
            Text(text = "Register User")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(text = "Already User?.")
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "SinIn Here",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    onNavigate(UiEvent.Navigate(Route.SinInScreen))
                })
        }

        sinUpFlow.value?.let {
            when(it){
                is Resources.Success -> {
                        onNavigate(UiEvent.Navigate(Route.SinInScreen))
                    Toast.makeText(context, "SinUp Successfully", Toast.LENGTH_SHORT).show()

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
package com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmexpensetrackerapp.R
import com.example.mvvmexpensetrackerapp.core.navigation.Route
import com.example.mvvmexpensetrackerapp.core.utils.UiEvent
import com.example.mvvmexpensetrackerapp.ui.theme.DarkGreen
import com.example.mvvmexpensetrackerapp.ui.theme.DarkPurple
import com.example.mvvmexpensetrackerapp.ui.theme.LightPurple
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
) {

    val scale = remember{
        Animatable(0f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.9f ,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            )
        )
        delay(500L)
        onNavigate(UiEvent.Navigate(Route.SinUpScreen))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(DarkPurple, LightPurple)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Surface(
            modifier = Modifier
                .size(450.dp)
                .scale(scale.value)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "image"
            )

        }

        Text(
            text = " Welcome To Expense Tracker App",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGreen
        )

    }

}
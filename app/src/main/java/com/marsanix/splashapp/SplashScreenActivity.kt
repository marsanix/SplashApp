package com.marsanix.splashapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.marsanix.splashapp.ui.theme.SplashAppTheme
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        this
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier, context: Context = ComponentActivity()) {

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {

        // atur efek animasi
        alpha.animateTo(5f,
            animationSpec = tween(
                300, 30, FastOutSlowInEasing
            )
        )

        // atur waktu perpindahan ke screen mainactivity
        delay(4500)
        context.startActivity(Intent(context, MainActivity::class.java))

    })

    Box(modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
        ) {
        Image(
            painter = painterResource(id = R.drawable.logo_il),
            contentDescription = "Logo IL"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SplashAppTheme {
        Greeting2("Android")
    }
}
package com.marsanix.splashapp

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.android.datatransport.BuildConfig
import com.marsanix.splashapp.ui.theme.SplashAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    // untuk memutar file audio tanpa ada media controller dan otomatis play
    /*
    val pathFile = "android.resource://" + LocalContext.current.packageName + "/" + R.raw.anaas
    val mediaItem = MediaItem.fromUri(Uri.parse(pathFile))
    val player = ExoPlayer.Builder(LocalContext.current).build()
    player.setMediaItem(mediaItem)
    player.prepare()
    player.play()
    */


    // putar video youtube
//    YoutubePlayer(
//        youtubeVideoId = "OIViEk9Sy_E",
//        lifecycleOwner = LocalLifecycleOwner.current
//    )

    // putar video local
    val path = "android.resource://" + LocalContext.current.packageName + "/" + R.raw.push_to_github
    val mediaSource = remember {
        MediaItem.fromUri(Uri.parse(path))
    }
    MovieContent(
        exoPlayer = ExoPlayer.Builder(LocalContext.current).build(),
        mediaSource = mediaSource,
        modifier = modifier
    )

    // putar file mp3 menggunakan library bawaan Android Studio
    // PlayMp3("anaas.mp3", LocalContext.current)


}




@Composable
fun MovieContent(
    exoPlayer: ExoPlayer,
    mediaSource:MediaItem,
    modifier: Modifier
) {
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
        // exoPlayer.play()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {

        AndroidView(
            factory = {ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            modifier = modifier.fillMaxWidth().height(200.dp)
        )

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplashAppTheme {
        Greeting("Android")
    }
}
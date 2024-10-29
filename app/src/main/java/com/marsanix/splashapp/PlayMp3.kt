package com.marsanix.splashapp

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

private var player: MediaPlayer? = null

@Composable
fun PlayMp3(fileName:String, context: Context) {

    LaunchedEffect(Unit) {
        val afd = context.assets.openFd(fileName)
        player = MediaPlayer().apply {
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            prepare()
            start()
        }
    }

}

private fun stop() {
    player?.stop()
    player?.release()
    player = null
}
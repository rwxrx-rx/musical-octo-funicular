package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer

class MainActivity : ComponentActivity() {
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inisialisasi ExoPlayer
        player = ExoPlayer.Builder(this).build()

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MusicPlayerScreen(player)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}

@Composable
fun MusicPlayerScreen(player: ExoPlayer?) {
    var isPlaying by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "High-Res Audio Player", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                // Contoh URL lagu open source (Ganti dengan URL FLAC/Lossless nanti)
                val streamUrl = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"
                val mediaItem = MediaItem.Builder()
                    .setUri(streamUrl)
                    .build()

                player?.setMediaItem(mediaItem)
                player?.prepare()
                player?.play()
                isPlaying = true
            }) {
                Text("Play Stream")
            }

            Button(onClick = {
                player?.pause()
                isPlaying = false
            }) {
                Text("Pause")
            }
        }
    }
}

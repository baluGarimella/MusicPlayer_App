package com.music.player.presentation.musicPlayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.music.player.R

//Activity for DisplayingMusic Player
class MusicPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player)
        supportActionBar?.hide()
    }
}
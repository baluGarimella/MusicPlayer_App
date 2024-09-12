package com.music.player.presentation.savedPlayList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.music.player.R

//Activity for showing saved playlist

class SavedPlayList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_playlist)
        supportActionBar?.hide()
    }
}
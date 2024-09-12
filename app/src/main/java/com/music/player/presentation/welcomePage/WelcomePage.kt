package com.music.player.presentation.welcomePage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.music.player.R

//Activity for  Welcome Page
class WelcomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)
        supportActionBar?.hide()
    }
}
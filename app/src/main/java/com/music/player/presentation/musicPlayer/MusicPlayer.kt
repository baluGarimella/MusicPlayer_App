@file:Suppress("DEPRECATION")

package com.music.player.presentation.musicPlayer

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.music.player.R
import com.music.player.SongsDataBase
import com.music.player.data.repository.MainRepositoryImpl
import com.music.player.domain.use_case.welcomeMusicList.MyViewModelFactory
import com.music.player.domain.use_case.welcomeMusicList.WelcomePageViewModel
import com.music.player.presentation.welcomePage.DataModel
import com.music.player.presentation.welcomePage.getMusicFromDevice
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MusicPlayer : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var platBtn:ImageView
    private lateinit var seekBar: SeekBar
    private lateinit var backBtn:ImageView
    private lateinit var forwardBtn:ImageView
    private lateinit var songTitle:TextView
    private var musicData:DataModel? = null
    private var  currentSongIndex = 0
    private lateinit var welcomePageViewModel: WelcomePageViewModel




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "  Music player"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.red)))
        window.statusBarColor=resources.getColor(R.color.red)


        musicData = intent.getSerializableExtra("data") as DataModel?
        Log.d("data",musicData.toString())
        songTitle=findViewById<TextView>(R.id.songTitle)
        val albumImage=findViewById<ImageView>(R.id.imageView)
        songTitle.text=musicData?.title
        forwardBtn=findViewById<ImageView>(R.id.next)
        backBtn=findViewById<ImageView>(R.id.prev)
        platBtn=findViewById<ImageView>(R.id.play)
        seekBar = findViewById(R.id.seekbar_time)

        val dao=SongsDataBase.getInstance(applicationContext).MusicPlayerDao()
        val mainRepository=MainRepositoryImpl(dao)
        val myViewModelFactory=MyViewModelFactory(mainRepository)
        welcomePageViewModel=ViewModelProvider(this,myViewModelFactory).get(WelcomePageViewModel::class.java)

        val filePath = musicData?.filePath

        mediaPlayer = MediaPlayer()

        forwardBtn.setOnClickListener {
            playNextSong(musicData)
        }
        backBtn.setOnClickListener {
            playPreviousSong(musicData)

        }



        try {
            mediaPlayer.setDataSource(filePath)
            mediaPlayer.prepare()
            play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        platBtn.setOnClickListener {
            play()
        }
        seekbarrunner()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.fav_icon->{
                GlobalScope.launch {   musicData?.let { welcomePageViewModel.insert(it) } }
                item.isVisible = false
                

                Toast.makeText(applicationContext,"dat",Toast.LENGTH_LONG).show()
                true
            }

            else -> { item.isVisible=true
                super.onOptionsItemSelected(item)}
        }
    }


    private fun seekbarrunner() {
        seekBar.progress=0
        seekBar.max=mediaPlayer.duration
        val handler = Handler()

        val runnable = object : Runnable {

            override fun run() {

                val currentPosition = mediaPlayer.currentPosition

                seekBar.progress = currentPosition

                handler.postDelayed(this, 1000)

            }

        }

        handler.postDelayed(runnable, 1000) // Initial post

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (fromUser) {

                    mediaPlayer.seekTo(progress)

                }

            }



            override fun onStartTrackingTouch(seekBar: SeekBar?) {


            }



            override fun onStopTrackingTouch(seekBar: SeekBar?) {


            }

        })
    }

    private fun play() {
        if (mediaPlayer.isPlaying ==true){
            mediaPlayer.pause()
            platBtn.setImageResource(R.drawable.play)

        }else {
            mediaPlayer.start()
            platBtn.setImageResource(R.drawable.stop)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.music_player_appbar,menu)
       val  menuItem= menu.findItem(R.id.search_action)
//        searchView= menuItem.actionView as SearchView
//        searchView.maxWidth = Int.MAX_VALUE
//        searchView.queryHint = "search here!!"
        return super.onCreateOptionsMenu(menu)
    }




    fun playNextSong(musicData: DataModel?) {
        currentSongIndex= getMusicFromDevice(applicationContext).indexOf(musicData)
        Toast.makeText(applicationContext,"$currentSongIndex",Toast.LENGTH_SHORT).show()
        if (currentSongIndex < getMusicFromDevice(applicationContext).size - 1) {
            currentSongIndex++
            playSong(currentSongIndex)
            seekbarrunner()

        }

    }



    fun playPreviousSong(musicData: DataModel?) {
        currentSongIndex= getMusicFromDevice(applicationContext).indexOf(musicData)
        Toast.makeText(applicationContext,"$currentSongIndex",Toast.LENGTH_SHORT).show()
        if (currentSongIndex > 0) {
            currentSongIndex--
            playSong(currentSongIndex)
            seekbarrunner()
        }

    }
    fun playSong(index: Int) {

        if (index >= 0 && index < getMusicFromDevice(applicationContext).size) {
            musicData= getMusicFromDevice(applicationContext)[index]
            songTitle.text=musicData?.title
            Toast.makeText(applicationContext,musicData.toString()+index.toString(),Toast.LENGTH_SHORT).show()
            mediaPlayer.reset()
            mediaPlayer.setDataSource(musicData?.filePath)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }

    }

}
package com.music.player.domain.repository

import androidx.lifecycle.LiveData
import com.music.player.presentation.welcomePage.DataModel

interface MainRepository {
    fun insertMusicItem(dataModel: DataModel)

    fun updateMusicItem(dataModel: DataModel)

    fun deleteMusicItem(dataModel: DataModel)

    fun deleteAllMusicItems()

    fun getAllMusicItems(): LiveData<List<DataModel>>
}
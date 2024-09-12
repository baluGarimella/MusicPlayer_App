package com.music.player.data.repository

import androidx.lifecycle.LiveData
import com.music.player.MusicPlayerDao
import com.music.player.domain.repository.MainRepository
import com.music.player.presentation.welcomePage.DataModel

class MainRepositoryImpl constructor(private var dao: MusicPlayerDao
) : MainRepository {
    override fun insertMusicItem(dataModel: DataModel) {

        dao.insertMusicItem(dataModel)
    }

    override fun updateMusicItem(dataModel: DataModel) {

         dao.updateMusicItem(dataModel)
    }

    override fun deleteMusicItem(dataModel: DataModel) {

         dao.deleteMusicItem(dataModel)
    }

    override fun deleteAllMusicItems() {

         dao.deleteAllMusicItems()
    }

    override fun getAllMusicItems(): LiveData<List<DataModel>> {

        return  dao.getAllMusicItems()
    }

}
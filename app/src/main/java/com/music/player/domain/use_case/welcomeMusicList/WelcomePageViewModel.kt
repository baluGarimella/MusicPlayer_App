package com.music.player.domain.use_case.welcomeMusicList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.music.player.domain.repository.MainRepository
import com.music.player.presentation.welcomePage.DataModel

// ViewModel for getting Music List from mobile and  Connecting UseCase and Activity of Welcome Page

class WelcomePageViewModel(private val userRepository: MainRepository) : ViewModel() {

     fun insert(dataModel: DataModel) {

        userRepository.insertMusicItem(dataModel)
    }

     fun update(dataModel: DataModel) {

         userRepository.updateMusicItem(dataModel)
    }

     fun delete(dataModel: DataModel) {

         userRepository.deleteMusicItem(dataModel)
    }

     fun deleteAllNotes() {

         userRepository.deleteAllMusicItems()
    }

     fun getAllNotes(): LiveData<List<DataModel>> {

        return  userRepository.getAllMusicItems()
    }


}


class MyViewModelFactory(private val userRepository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomePageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WelcomePageViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}
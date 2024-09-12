package com.music.player.domain.repository

/* Class For all logical operation please add all operation here and use in in MainRepositoryImpl*/
interface MainRepository {
    suspend fun getSongsList()
}
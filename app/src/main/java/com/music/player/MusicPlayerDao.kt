package com.music.player

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.music.player.presentation.welcomePage.DataModel

@Dao
interface MusicPlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMusicItem(dataModel: DataModel)

    @Update
    fun updateMusicItem(dataModel: DataModel)

    @Delete
    fun deleteMusicItem(dataModel: DataModel)

    @Query("delete from data")
    fun deleteAllMusicItems()

    @Query("select * from data order by title desc")
    fun getAllMusicItems(): LiveData<List<DataModel>>
}
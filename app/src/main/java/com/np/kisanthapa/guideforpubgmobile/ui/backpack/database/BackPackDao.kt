package com.np.kisanthapa.guideforpubgmobile.ui.backpack.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.model.BackPackModelClass

@Dao
interface BackPackDao {

    @Query("SELECT * FROM table_backpack")
    fun getBagPacks(): LiveData<List<BackPackModelClass>>

    @Query("SELECT * FROM table_backpack WHERE id =:backPackId")
    fun getBackPackById(backPackId: Int): LiveData<BackPackModelClass>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setBagPack(backPackModel: BackPackModelClass)

}
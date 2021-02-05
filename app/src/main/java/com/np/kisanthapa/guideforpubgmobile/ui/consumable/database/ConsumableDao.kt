package com.np.kisanthapa.guideforpubgmobile.ui.consumable.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.model.ConsumableModel

@Dao
interface ConsumableDao {

    @Query("SELECT * FROM table_consumable")
    fun getAllConsumableItems(): LiveData<List<ConsumableModel>>

}
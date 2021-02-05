package com.np.kisanthapa.guideforpubgmobile.ui.armor.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.armor.model.ArmorModelClass

@Dao
interface ArmorDao {
    @Query("SELECT * FROM table_armor")
    fun getAllArmors(): LiveData<List<ArmorModelClass>>
}
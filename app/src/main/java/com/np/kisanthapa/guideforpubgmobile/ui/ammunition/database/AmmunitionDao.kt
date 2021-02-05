package com.np.kisanthapa.guideforpubgmobile.ui.ammunition.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model.AmmunitionModel

@Dao
interface AmmunitionDao {

    @Query("SELECT * FROM table_ammunition")
    fun getAllAmmunition(): LiveData<List<AmmunitionModel>>
}
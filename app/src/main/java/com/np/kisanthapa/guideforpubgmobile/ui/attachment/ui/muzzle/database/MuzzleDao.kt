package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model.MuzzleModel

@Dao
interface MuzzleDao {

    @Query("SELECT * FROM table_muzzle")
    fun getAllMuzzleData(): LiveData<List<MuzzleModel>>

}
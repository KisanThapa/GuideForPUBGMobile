package com.np.kisanthapa.guideforpubgmobile.ui.helmet.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClass

@Dao
interface HelmetDao {

    @Query("SELECT * FROM table_helmet")
    fun getAllHelmets(): LiveData<List<HelmetModelClass>>
}
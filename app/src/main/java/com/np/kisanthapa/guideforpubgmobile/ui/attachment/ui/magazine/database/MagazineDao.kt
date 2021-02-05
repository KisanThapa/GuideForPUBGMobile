package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagazineModel

@Dao
interface MagazineDao {

    @Query("SELECT * FROM table_magazine")
    fun getAllMagazine(): LiveData<List<MagazineModel>>
}
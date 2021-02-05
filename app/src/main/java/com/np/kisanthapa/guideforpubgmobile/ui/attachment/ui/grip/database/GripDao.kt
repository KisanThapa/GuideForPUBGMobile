package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model.GripModel

@Dao
interface GripDao {

    @Query("SELECT * FROM table_grip")
    fun getAllGripData(): LiveData<List<GripModel>>

}
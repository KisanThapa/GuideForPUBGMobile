package com.np.kisanthapa.guideforpubgmobile.ui.throwable.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.model.ThrowableModelClass

@Dao
interface ThrowableDao {

    @Query("SELECT * FROM table_throwable")
    fun getAllThrowable(): LiveData<List<ThrowableModelClass>>

}
package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel

@Dao
interface ScopeDao {
    @Query("SELECT * FROM table_scope")
    fun getScopeList(): LiveData<List<ScopeModel>>

}
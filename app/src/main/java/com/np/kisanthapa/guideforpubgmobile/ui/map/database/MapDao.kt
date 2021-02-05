package com.np.kisanthapa.guideforpubgmobile.ui.map.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapDropLocationModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapInfoModelClass

@Dao
interface MapDao {

    @Query("SELECT * FROM table_map_info WHERE id = :mapId")
    fun getMapInfoById(mapId: Int): LiveData<List<MapInfoModelClass>>


    @Query("SELECT * FROM table_map_drop_location WHERE map_id = :mapId")
    fun getMapDropLocationById(mapId: Int): LiveData<List<MapDropLocationModelClass>>


}
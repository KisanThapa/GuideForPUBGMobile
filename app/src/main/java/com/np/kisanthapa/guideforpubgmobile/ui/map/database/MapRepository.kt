package com.np.kisanthapa.guideforpubgmobile.ui.map.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapDropLocationModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapInfoModelClass

class MapRepository(private val mapDao: MapDao, val mapId: Int) {

    val mapInfoData: LiveData<List<MapInfoModelClass>> =
        mapDao.getMapInfoById(mapId)

    val mapDropLocationData: LiveData<List<MapDropLocationModelClass>> =
        mapDao.getMapDropLocationById(mapId)

}
package com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.map.database.MapRepository
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapInfoModelClass

class MapInformationViewModel(application: Application, mapId: Int) :
    AndroidViewModel(application) {

    val mapInfo: LiveData<List<MapInfoModelClass>>

    private val mMapRepository: MapRepository

    init {
        val mapDao = MyRoomDatabase.getDatabase(application).mapDao()
        mMapRepository = MapRepository(mapDao, mapId)

        mapInfo = mMapRepository.mapInfoData

    }

}

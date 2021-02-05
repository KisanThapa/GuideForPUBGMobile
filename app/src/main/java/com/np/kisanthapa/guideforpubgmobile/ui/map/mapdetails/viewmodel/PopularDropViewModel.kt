package com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.map.database.MapRepository
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapDropLocationModelClass

class PopularDropViewModel(application: Application, val mapId: Int) :
    AndroidViewModel(application) {

    val dropLocation: LiveData<List<MapDropLocationModelClass>>

    //Repository
    private var mapRepository: MapRepository

    init {

        val mapDao = MyRoomDatabase.getDatabase(application).mapDao()

        mapRepository = MapRepository(mapDao, mapId)

        dropLocation = mapRepository.mapDropLocationData
    }

}

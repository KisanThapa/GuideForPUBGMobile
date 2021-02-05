package com.np.kisanthapa.guideforpubgmobile.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapModelClass

class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val _mapDataList = MutableLiveData<List<MapModelClass>>()
        .apply {
            value = createMapData()
        }

    val mapDataList: LiveData<List<MapModelClass>> = _mapDataList

    private fun createMapData(): List<MapModelClass> {

        return listOf(
            MapModelClass(
                1,
                "Erangel",
                "item_map_erangel_thumbnail",
                "item_map_erangel_map_information",
                "item_map_erangel_popular_drop",
                "item_map_erangel_map_information"
            ),
            MapModelClass(
                2,
                "Miramar",
                "item_map_miramar_thumbnail",
                "item_map_miramar_map_information",
                "item_map_miramar_popular_drop",
                "item_map_miramar_map_information"
            ),
            MapModelClass(
                3,
                "Sanhok",
                "item_map_sanhok_thumbnail",
                "item_map_sanhok_map_information",
                "item_map_sanhok_popular_drop",
                "item_map_sanhok_map_information"
            ),
            MapModelClass(
                4,
                "Vikendi",
                "item_map_vikendi_thumbnail",
                "item_map_vikendi_map_information",
                "item_map_vikendi_popular_drop",
                "item_map_vikendi_map_information"

            ),
            MapModelClass(
                5,
                "Training Map",
                "item_map_training_thumbnail",
                "item_map_vikendi_map_information",
                "item_map_vikendi_popular_drop",
                "item_map_vikendi_map_information"
            )
        )
    }
}

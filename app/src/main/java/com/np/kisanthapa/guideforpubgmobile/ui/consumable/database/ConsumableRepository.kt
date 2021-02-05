package com.np.kisanthapa.guideforpubgmobile.ui.consumable.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.model.ConsumableModel

class ConsumableRepository(private val consumableDao: ConsumableDao) {

    val allConsumableItems: LiveData<List<ConsumableModel>> =
        consumableDao.getAllConsumableItems()

}
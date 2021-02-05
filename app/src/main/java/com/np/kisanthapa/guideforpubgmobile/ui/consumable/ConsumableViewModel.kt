package com.np.kisanthapa.guideforpubgmobile.ui.consumable

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.database.ConsumableRepository
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.model.ConsumableModel

class ConsumableViewModel(application: Application) : AndroidViewModel(application) {

    var consumableList: LiveData<List<ConsumableModel>>

    // Instance of ConsumableRepository to access database actions
    private val mConsumableRepository: ConsumableRepository

    init {
        val consumableDao = MyRoomDatabase.getDatabase(application).consumableDao()
        mConsumableRepository = ConsumableRepository(consumableDao)

        consumableList = mConsumableRepository.allConsumableItems
    }

}

package com.np.kisanthapa.guideforpubgmobile.ui.ammunition.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.database.AmmunitionRepository
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model.AmmunitionModel

class AmmunitionViewModel(application: Application) : AndroidViewModel(application) {

    var ammunitionList: LiveData<List<AmmunitionModel>>

    // Instance of AmmunitionRepository to access database actions
    private val mAmmunitionRepository: AmmunitionRepository

    init {
        val ammunitionDao = MyRoomDatabase.getDatabase(application).ammunitionDao()
        mAmmunitionRepository = AmmunitionRepository(ammunitionDao)

        ammunitionList = mAmmunitionRepository.ammunitionData
    }

}

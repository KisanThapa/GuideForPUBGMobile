package com.np.kisanthapa.guideforpubgmobile.ui.ammunition.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model.AmmunitionModel

class AmmunitionRepository(private val ammunitionDao: AmmunitionDao) {

    val ammunitionData: LiveData<List<AmmunitionModel>>
            = ammunitionDao.getAllAmmunition()

}
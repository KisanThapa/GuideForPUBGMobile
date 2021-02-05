package com.np.kisanthapa.guideforpubgmobile.ui.armor.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.armor.model.ArmorModelClass

class ArmorRepository(private val armorDao: ArmorDao) {

    val allArmors: LiveData<List<ArmorModelClass>>
            = armorDao.getAllArmors()

}
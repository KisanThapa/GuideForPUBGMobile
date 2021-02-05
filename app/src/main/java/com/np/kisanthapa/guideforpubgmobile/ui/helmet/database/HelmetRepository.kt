package com.np.kisanthapa.guideforpubgmobile.ui.helmet.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClass

class HelmetRepository(private val helmetDao: HelmetDao) {

    val allHelmets: LiveData<List<HelmetModelClass>> =
        helmetDao.getAllHelmets()

}
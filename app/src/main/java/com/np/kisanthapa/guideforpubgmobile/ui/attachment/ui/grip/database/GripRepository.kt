package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model.GripModel

class GripRepository(private val gripDao: GripDao) {

    val gripDataList: LiveData<List<GripModel>> = gripDao.getAllGripData()

}
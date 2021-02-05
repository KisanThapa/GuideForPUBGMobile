package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model.MuzzleModel

class MuzzleRepository(private val muzzleDao: MuzzleDao) {

    val muzzleListData: LiveData<List<MuzzleModel>> = muzzleDao.getAllMuzzleData()
}
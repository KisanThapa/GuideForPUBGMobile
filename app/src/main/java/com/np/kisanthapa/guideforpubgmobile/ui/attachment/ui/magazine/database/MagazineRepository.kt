package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagazineModel

class MagazineRepository(private val magazineDao: MagazineDao) {

   val magazineListData:LiveData<List<MagazineModel>> = magazineDao.getAllMagazine()

}
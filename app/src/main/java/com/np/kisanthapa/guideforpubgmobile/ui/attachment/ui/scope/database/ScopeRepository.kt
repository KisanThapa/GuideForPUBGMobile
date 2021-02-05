package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel

class ScopeRepository(private val scopeDao: ScopeDao) {

    val scopeListData: LiveData<List<ScopeModel>> = scopeDao.getScopeList()

}
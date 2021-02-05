package com.np.kisanthapa.guideforpubgmobile.ui.throwable.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.model.ThrowableModelClass

class ThrowableRepository(private val throwableDao: ThrowableDao) {

    val allThrowable: LiveData<List<ThrowableModelClass>> = throwableDao.getAllThrowable()

}
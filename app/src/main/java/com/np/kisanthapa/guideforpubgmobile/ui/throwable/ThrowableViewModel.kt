package com.np.kisanthapa.guideforpubgmobile.ui.throwable

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.database.ThrowableDao
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.database.ThrowableRepository
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.model.ThrowableModelClass

class ThrowableViewModel(application: Application) : AndroidViewModel(application) {

    var throwableList: LiveData<List<ThrowableModelClass>>

    // Instance of ThrowableRepository to access database actions
    private val mThrowableRepository: ThrowableRepository

    init {
        val throwableDao: ThrowableDao = MyRoomDatabase.getDatabase(application).throwableDao()
        mThrowableRepository = ThrowableRepository(throwableDao)

        throwableList = mThrowableRepository.allThrowable
    }

}

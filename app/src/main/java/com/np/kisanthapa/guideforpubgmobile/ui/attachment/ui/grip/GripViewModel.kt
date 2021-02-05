package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.database.GripRepository
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model.GripModel

class GripViewModel(application: Application) : AndroidViewModel(application) {

    // This value will be observed
    val gripList: LiveData<List<GripModel>>

    //Declare Repository
    private val mGripRepository: GripRepository

    init {
        val gripDao = MyRoomDatabase.getDatabase(application).gripDao()
        mGripRepository = GripRepository(gripDao)
        gripList = mGripRepository.gripDataList
    }

}
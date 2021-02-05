package com.np.kisanthapa.guideforpubgmobile.ui.helmet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.database.HelmetRepository
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClass

class HelmetViewModel(application: Application) : AndroidViewModel(application) {

    var helmetList: LiveData<List<HelmetModelClass>>

    // Instance of HelmetRepository to access database actions
    private val mHelmetRepository: HelmetRepository

    init {
        val helmetDao = MyRoomDatabase.getDatabase(application).helmetDao()
        mHelmetRepository = HelmetRepository(helmetDao)

        helmetList = mHelmetRepository.allHelmets
    }
}

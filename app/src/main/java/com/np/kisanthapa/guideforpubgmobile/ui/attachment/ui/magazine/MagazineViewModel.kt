package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.database.MagazineRepository
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagazineModel

class MagazineViewModel(application: Application) : AndroidViewModel(application) {

    // This value will be observed
    val magazineList: LiveData<List<MagazineModel>>

    //Declare Repository
    private val mMagazineRepository: MagazineRepository

    init {
        val magazineDao = MyRoomDatabase.getDatabase(application).magazineDao()

        mMagazineRepository = MagazineRepository(magazineDao)

        magazineList = mMagazineRepository.magazineListData

    }

}
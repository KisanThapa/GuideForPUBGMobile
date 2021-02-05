package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.database.MuzzleRepository
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model.MuzzleModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.database.ScopeRepository

class MuzzleViewModel(application: Application) : AndroidViewModel(application) {

    // This value will be observed
    val muzzleList: LiveData<List<MuzzleModel>>

    //Declare Repository
    private val mMuzzleRepository: MuzzleRepository

    init {
        val muzzleDao = MyRoomDatabase.getDatabase(application).muzzleDao()

        mMuzzleRepository = MuzzleRepository(muzzleDao)

        muzzleList = mMuzzleRepository.muzzleListData

    }

}
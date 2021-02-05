package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.database.ScopeRepository
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel

class ScopeViewModel(application: Application) : AndroidViewModel(application) {

    // This value will be observed
    val scopeList: LiveData<List<ScopeModel>>

    //Declare Repository
    private val mScopeRepository: ScopeRepository

    init {
        val scopeDao = MyRoomDatabase.getDatabase(application).scopeDao()

        mScopeRepository = ScopeRepository(scopeDao)

        scopeList = mScopeRepository.scopeListData

    }

}
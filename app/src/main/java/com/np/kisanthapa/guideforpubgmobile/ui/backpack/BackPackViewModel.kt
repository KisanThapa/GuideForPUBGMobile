package com.np.kisanthapa.guideforpubgmobile.ui.backpack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.database.BackPackRepository
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.model.BackPackModelClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the BagPack repository and
 * an up-to-date list of all Bags.
 */

class BackPackViewModel(application: Application) : AndroidViewModel(application) {

    var backList: LiveData<List<BackPackModelClass>>

    // Instance of BackPackRepository to access database actions
    private val mBackPackRepository: BackPackRepository

    init {
        val bagPackDao = MyRoomDatabase.getDatabase(application).backPackDao()
        mBackPackRepository = BackPackRepository(bagPackDao)

        backList = mBackPackRepository.allBags
    }

}

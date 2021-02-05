package com.np.kisanthapa.guideforpubgmobile.ui.backpack.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.model.BackPackModelClass
import kotlin.properties.Delegates

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class BackPackRepository(private val backPackDao: BackPackDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    val allBags: LiveData<List<BackPackModelClass>> = backPackDao.getBagPacks()


    //var singleBackPack: LiveData<BackPackModelClass> = backPackDao.getBackPackById()

    fun getBackPackById(backPackId: Int) = backPackDao.getBackPackById(backPackId)


    suspend fun setBagPack(backPackModel: BackPackModelClass) {
        backPackDao.setBagPack(backPackModel)
    }

}
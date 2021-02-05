package com.np.kisanthapa.guideforpubgmobile.ui.armor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.armor.database.ArmorDao
import com.np.kisanthapa.guideforpubgmobile.ui.armor.database.ArmorRepository
import com.np.kisanthapa.guideforpubgmobile.ui.armor.model.ArmorModelClass

class ArmorViewModel(application: Application) : AndroidViewModel(application) {

    var armorList: LiveData<List<ArmorModelClass>>

    // Instance of ArmorRepository to access database actions
    private val mArmorRepository: ArmorRepository

    init {
        val armorDao: ArmorDao = MyRoomDatabase.getDatabase(application).armorDao()
        mArmorRepository = ArmorRepository(armorDao)

        armorList = mArmorRepository.allArmors
    }
}

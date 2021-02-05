package com.np.kisanthapa.guideforpubgmobile.ui.vehicle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.database.VehicleRepository
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClass

class VehicleViewModel(application: Application) : AndroidViewModel(application) {

    // This value will be observed
    val vehicleList: LiveData<List<VehicleModelClass>>


    //Declare Repository
    private val mVehicleRepository: VehicleRepository

    init {
        val vehicleDao = MyRoomDatabase.getDatabase(application).vehicleDao()

        mVehicleRepository = VehicleRepository(vehicleDao)

        vehicleList = mVehicleRepository.allVehicles

    }
}

package com.np.kisanthapa.guideforpubgmobile.ui.vehicle.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClass

class VehicleRepository(private val vehicleDao: VehicleDao) {

    val allVehicles: LiveData<List<VehicleModelClass>> = vehicleDao.getAllVehicles()

}
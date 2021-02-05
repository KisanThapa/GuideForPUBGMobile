package com.np.kisanthapa.guideforpubgmobile.ui.vehicle.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClass

@Dao
interface VehicleDao {

    @Query("SELECT * FROM table_vehicle")
    fun getAllVehicles(): LiveData<List<VehicleModelClass>>

}
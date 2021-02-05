package com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_vehicle")
data class VehicleModelClass(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val vehicleId: Int,
    @ColumnInfo(name = "name") val vehicleName: String,
    @ColumnInfo(name = "summary") val vehicleSummary: String,
    @ColumnInfo(name = "feature") val vehicleFeature: String,
    @ColumnInfo(name = "occupants") val vehicleOccupants: Int,
    @ColumnInfo(name = "health") val vehicleHealth: Int,
    @ColumnInfo(name = "top_speed") val vehicleTopSpeed: String,
    @ColumnInfo(name = "type") val vehicleType: String,
    @ColumnInfo(name = "appears_in") val vehicleAppearsIn: String,
    @ColumnInfo(name = "image_name") val vehicleImageName: String,
    @ColumnInfo(name = "icon_image_name") val vehicleIconImageName: String
)
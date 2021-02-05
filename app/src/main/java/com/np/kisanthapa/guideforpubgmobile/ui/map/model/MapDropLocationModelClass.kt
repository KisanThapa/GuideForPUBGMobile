package com.np.kisanthapa.guideforpubgmobile.ui.map.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_map_drop_location")
data class MapDropLocationModelClass(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val locationId: Int,
    @ColumnInfo(name = "map_id") val mapId: Int,
    @ColumnInfo(name = "location_name") val mapLocationName: String,
    @ColumnInfo(name = "description") val mapDescription: String,
    @ColumnInfo(name = "popularity") val mapPopularity: String,
    @ColumnInfo(name = "expected_equipment") val mapExpectedEquipment: String,
    @ColumnInfo(name = "vehicles") val mapVehicle: String,
    @ColumnInfo(name = "image_name") val mapImageName: String
)
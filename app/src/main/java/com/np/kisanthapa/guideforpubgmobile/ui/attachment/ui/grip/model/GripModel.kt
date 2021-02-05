package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_grip")
data class GripModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val gripId: Int,
    @ColumnInfo(name = "name") val gripName: String,
    @ColumnInfo(name = "summary") val gripSummary: String,
    @ColumnInfo(name = "capacity") val gripCapacity: String,
    @ColumnInfo(name = "feature") val gripFeature: String,
    @ColumnInfo(name = "attachable_weapon_names") val gripSupportedWeaponName: String,
    @ColumnInfo(name = "attachable_weapon_images") val gripSupportedWeaponImage: String,
    @ColumnInfo(name = "image") val gripImage: String
)